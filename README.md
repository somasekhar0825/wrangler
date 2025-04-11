## 📄 prompts.txt

This file contains sample usage of the custom directive `aggregate-stats`.

### Examples:

```text
# Aggregating time durations
aggregate-stats field:duration type:time

# Aggregating download size in bytes
aggregate-stats field:download_size type:size

# Another example with network data
aggregate-stats field:network_traffic type:size

# Multiple fields
aggregate-stats field:uptime type:time
aggregate-stats field:data_used type:size
