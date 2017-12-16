package com.fngry.expression;

import com.fngry.expression.execute.IExecutor;
import com.fngry.expression.execute.groovy.GroovyExecutor;

/**
 * expression runner
 * choose executor to calculate value of expression
 */
public class ExpressionRunner {

    public static IExecutor getDefaultExecutor() {
        return new GroovyExecutor();
    }

    public static IExecutor getGroovyExecutor() {
        return new GroovyExecutor();
    }

}
