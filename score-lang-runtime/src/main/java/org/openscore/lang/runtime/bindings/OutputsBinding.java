/*******************************************************************************
* (c) Copyright 2014 Hewlett-Packard Development Company, L.P.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Apache License v2.0 which accompany this distribution.
*
* The Apache License is available at
* http://www.apache.org/licenses/LICENSE-2.0
*
*******************************************************************************/

package org.openscore.lang.runtime.bindings;

import org.openscore.lang.entities.bindings.Output;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.openscore.lang.entities.ScoreLangConstants.BIND_OUTPUT_FROM_INPUTS_KEY;

/**
 * Date: 11/7/2014
 *
 * @author Bonczidai Levente
 */
@Component
public class OutputsBinding {

    @Autowired
    ScriptEvaluator scriptEvaluator;

    public Map<String, String> bindOutputs(Map<String, Serializable> inputs,
                                           Map<String, String> actionReturnValues,
                                           List<Output> possibleOutputs) {

        Map<String, String> outputs = new LinkedHashMap<>();
        if (possibleOutputs != null) {
            for (Output output : possibleOutputs) {
                String outputKey = output.getName();
                String outputExpr = output.getExpression();
                if (outputExpr != null) {
                    //construct script context
                    Map<String, Serializable> scriptContext = new HashMap<>();
                    //put action outputs
                    scriptContext.putAll(actionReturnValues);
                    //put operation inputs as a map
                    if(MapUtils.isNotEmpty(inputs)) {
                        scriptContext.put(BIND_OUTPUT_FROM_INPUTS_KEY, (Serializable) inputs);
                    }

                    //evaluate expression
                    Serializable scriptResult = scriptEvaluator.evalExpr(outputExpr, scriptContext);

                    if (scriptResult != null) {
                        try {
                            outputs.put(outputKey, (String) scriptResult);
                        } catch (ClassCastException ex) {
                            throw new RuntimeException("The output expression " + outputExpr + " does not return String value", ex);
                        }
                    }
                } else {
                    throw new RuntimeException("Output: " + outputKey + " has no expression");
                }
            }
        }
        return outputs;
    }

}
