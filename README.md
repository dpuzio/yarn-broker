# yarn-broker

Cloud foundry broker for YARN.

## Configure

For strict separation of config from code (twelve-factor principle), configuration must be placed in environment variables.

Yarn-broker can be configured to work with hadoop in secure mode (authentication by Kerberos, value of hadoop.security.authentication property to "kerberos"),
called further **secure profile** configuration, or with hadoop in insecure mode (value of hadoop.security.authentication property to "simple"),
**insecure profile** configuration.

Broker configuration params list (environment properties):
* obligatory only on secure profile :
  * KRB_KDC_HOST - kerberos kdc host address
  * KRB_REALM - kerberos realm name
* optional :
  * HADOOP_PROVIDED_PARAMS - list of hadoop configuration parameters exposed by service (json format, default: {})

For instance.:

Secure profile configuration.
```
cf se hdfs-broker KRB_KDC_HOST ip-10-10-9-198.us-west-2.compute.internal
cf se hdfs-broker KRB_REALM US-WEST-2.COMPUTE.INTERNAL
```