# wrangler
Modified Wrangler  with byte size and time duration unit support 

# Wrangler Enhancement: ByteSize and TimeDuration Units

This repository contains enhancements to the CDAP Wrangler library for parsing and aggregating byte size and time duration units.

## Features Added

- Parsing support for values like `10KB`, `2MB`, `1.5GB`
- Parsing support for durations like `100ms`, `1.2s`, `3m`, `2h`
- New directive: `aggregate-stats` for aggregating data size and time duration

## Usage

Example directive usage:

```wrangler
aggregate-stats :size_column :duration_column total_size_mb total_time_sec
