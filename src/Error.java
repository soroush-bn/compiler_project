import java.util.List;

public record Error(Type type, int line, int column, List<String> fields) {
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
    public static enum Type {
        CLASS_ALREADY_DEFINED(101),
        METHOD_ALREADY_DEFINED(102),
        PARAMETER_ALREADY_DEFINED(103),
        FIELD_ALREADY_DEFINED(104),
        CLASS_NOT_FOUND(105),
        VAR_NOT_FOUND(106),
        RETURN_TYPE_NOT_MATCH(210),
        MISMATCH_ARGUMENT(220),
        INCOMPATIBLE_TYPES(230),

        ;


        final private int code;
        Type(int errorCode) {
            code = errorCode;
        }
    }
    @Override
    public String toString() {

        return ANSI_RED + "Error" + type.code + " in line " + line + ":" + column + ", " + switch (type) {
            case CLASS_ALREADY_DEFINED -> "class " + fields.get(0) + " has been defined already";
            case METHOD_ALREADY_DEFINED -> "method " + fields.get(0) + " has been defined already";
            case PARAMETER_ALREADY_DEFINED -> "parameter " + fields.get(0) + " has been defined already";
            case FIELD_ALREADY_DEFINED -> "field " + fields.get(0) + " has been defined already";
            case CLASS_NOT_FOUND -> "cannot find class " + fields.get(0);
            case VAR_NOT_FOUND -> "cannot find variable" + fields.get(0);
            case RETURN_TYPE_NOT_MATCH -> "ReturnType of method must be " + fields.get(0);
            case MISMATCH_ARGUMENT -> "Mismatch arguments.";
            case INCOMPATIBLE_TYPES -> "Incompatible types : " + fields.get(0) + "can not be converted to " + fields.get(1);
            default->
                throw new IllegalStateException("Unexpected value: " + type);
        };
    }

}


