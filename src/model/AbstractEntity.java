package model;

public abstract class AbstractEntity implements IdentifiableEntity{
    private static long id = 0;

    public AbstractEntity(){
        ++id;
    }

    @Override
    public long getId() {
        return id;
    }
}
