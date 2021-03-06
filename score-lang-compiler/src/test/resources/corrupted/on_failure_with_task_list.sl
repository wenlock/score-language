#   (c) Copyright 2014 Hewlett-Packard Development Company, L.P.
#   All rights reserved. This program and the accompanying materials
#   are made available under the terms of the Apache License v2.0 which accompany this distribution.
#
#   The Apache License is available at
#   http://www.apache.org/licenses/LICENSE-2.0
namespace: org.openscore

imports:
  ops: org.openscore

flow:
  name: on_failure_with_task_list
  workflow:
    task1:
      do:
        ops.op1:
    on_failure:
      - task2:
          do:
            ops.op1
