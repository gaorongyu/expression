package com.fngry.expression.execute;

public abstract class AbstractExecutor implements IExecutor {

    protected boolean useCache;

    protected AbstractExecutor(boolean useCache) {
        this.useCache = useCache;
    }

    public boolean isUseCache() {
        return useCache;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

}
