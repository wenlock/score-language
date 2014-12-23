package org.openscore.lang.compiler.model;/*
 * Licensed to Hewlett-Packard Development Company, L.P. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
*/

import org.openscore.lang.compiler.SlangTextualKeys;
import org.openscore.lang.entities.bindings.Input;
import org.openscore.lang.entities.bindings.Output;
import org.openscore.lang.entities.bindings.Result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
 * Created by orius123 on 09/11/14.
 */
public class Operation extends Executable {

    private final Action action;

    public Operation(Map<String, Serializable> preOpActionData,
                     Map<String, Serializable> postOpActionData,
                     Action action,
                     String namespace,
                     String name,
                     List<Input> inputs,
                     List<Output> outputs,
                     List<Result> results) {
        super(preOpActionData, postOpActionData, namespace, name, inputs, outputs, results);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public String getType() {
        return SlangTextualKeys.OPERATION_TYPE;
    }
}