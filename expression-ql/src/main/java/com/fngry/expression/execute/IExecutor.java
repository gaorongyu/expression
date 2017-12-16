package com.fngry.expression.execute;

import java.util.Map;

public interface IExecutor {

    Object execute(String expression, Map<String, Object> context) throws Exception;

    Object execute(String expression) throws Exception;

}
