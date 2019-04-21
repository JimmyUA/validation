package validation.validators.validatorspool;

import validation.domain.Dto;
import validation.validators.DtoFieldValidator;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;

public enum  ValidatorsPool {

    POOL;

    private transient ConcurrentHashMap<Class<? extends DtoFieldValidator>, DtoFieldValidator<Annotation, Dto>> pool =
            new ConcurrentHashMap<>();

    public DtoFieldValidator<Annotation, Dto> getValidator(Class<? extends DtoFieldValidator> type){
        DtoFieldValidator<Annotation, Dto> validator = pool.get(type);
        if (validator == null){
            validator = createValidator(type);
            validator.lock();
            pool.put(type,validator);
            return validator;
        } else{
            if(validator.isLocked()){
                return createValidator(type);
            }
            validator.lock();
            return validator;
        }
    }

    @SuppressWarnings("unchecked")
    private DtoFieldValidator<Annotation, Dto> createValidator(Class<? extends DtoFieldValidator> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Can not create Validator");
        }
    }
}
