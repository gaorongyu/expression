package com.fngry.expression.execute.groovy;

import com.fngry.expression.execute.AbstractExecutor;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.util.HashMap;
import java.util.Map;

/**
 * Groovy Executor
 */
public class GroovyExecutor extends AbstractExecutor {

    /**
     * the groovy shell
     */
    private GroovyShell shell = new GroovyShell();

    /**
     * cache
     */
    private Map<String, Script> cache = new HashMap<>();

    public GroovyExecutor() {
        super(true);
    }

    @Override
    public Object execute(String expression, Map<String, Object> context) throws Exception {
        // binding execute context
        Binding binding = new Binding(context);
        // parse expression
        Script script = null;
        if (this.useCache) {
            script = getScriptFromCache(expression);
        } else {
            script = shell.parse(expression);
        }
        // set binding
        script.setBinding(binding);
        // execute expression on context and return execute result
        return script.run();
    }

    @Override
    public Object execute(String expression) throws Exception {
        return execute(expression, null);
    }

    private Script getScriptFromCache(String expression) {
        if (cache.containsKey(expression)) {
            return cache.get(expression);
        }
        synchronized (this) {
            Script script = shell.parse(expression);
            cache.put(expression, script);
            return cache.get(expression);
        }
    }

}
