Huahin Tools

Huahin Tools is set of tools ont the Hadoop MapReduce.

Huahin Tools is distributed under Apache License 2.0.


-----------------------------------------------------------------------------
Documentation
  http://huahinframework.org/huahin-tools/

-----------------------------------------------------------------------------
Requirements
  * Java 6+

-----------------------------------------------------------------------------
Install Huahin Tools
  ~ $ tar xzf huahin-tools-x.x.x.tar.gz

-----------------------------------------------------------------------------
Amzon Elastic MapReduce
  jar fila path
    s3://huahin/tools/huahin-tools-x.x.x.jar

-----------------------------------------------------------------------------
Run Huahin Tools

To run Huahin Tools use bin/huahin-tools script. For example:

  $ bin/huahin-tools -j formatting -i ../input/ -o output

-----------------------------------------------------------------------------
Common Arguments

  -l local mode. Map is running MultithreadedMapper.
  -t thread number option. MultithreadedMapper number of thread.
  -s split size.
  -j job name.

-----------------------------------------------------------------------------
Tools

* Formatting
    Formatting is formed after the split with a regular expression to the specified input file.
    The default is the format of the default Apache log, so if you do not specify a regular expression.

  arguments
    required
      -i data input path.
      -o data output path.

    option
      -p separator pattern. Specify a regular expression. Default is default Apache log format.
      -e specific outputs number. Specify a regular expression output group number.
      -n group number of specify a regular expression.

  For example:

    ./bin/huahin-tools -l -t 4 -s 2147483648 -j formatting -i /tmp/input/ -o /tmp/output/ -e 0,3,5 -n 11
