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

    $ ./bin/huahin-tools -l -t 4 -s 2147483648 \
       -j formatting \
       -i /tmp/input/ \
       -o /tmp/output/ \
       -e 0,3,5 \
       -n 11

* wc
    wc is the wc command of Linux(-l option only).

  arguments
    required
      -i data input path.
      -o data output path.

  For example:

    $ ./bin/huahin-tools -l -t 4 -s 2147483648 \
       -j wc \
       -i /tmp/input/ \
       -o /tmp/output/

* cut
    cut is th cut command of Linux.

  arguments
    required
      -i data input path.
      -o data output path.

    option
      -f specified column. 1 or 1,2 or 1-4.
      -d delimiter. Default TAB.

  For example:

    $ ./bin/huahin-tools -l -t 4 -s 2147483648 \
       -j cut \
       -i /tmp/input/ \
       -o /tmp/output/ \
       -f 3,4

* ccext
    ccext extracts the row number of the specified column.

  arguments
    required
      -i data input path.
      -o data output path.

    option
      -n Number of the specified column.
      -v reverse of -n.
      -d delimiter. Default TAB.

  For example:

    $ ./bin/huahin-tools -l -t 4 -s 2147483648 \
       -j ccext \
       -i /tmp/input/ \
       -o /tmp/output/ \
       -n 12 \
       -v

* urldec
    urldec is to URL decode the specified columns.

  arguments
    required
      -i data input path.
      -o data output path.

    option
      -f decode the specified columns. 1 or 1,2 or 1-4.
      -d delimiter. Default TAB.

  For example:

    $ ./bin/huahin-tools -l -t 4 -s 2147483648 \
       -j urldec \
       -i /tmp/input/ \
       -o /tmp/output/ \
       -f 3,5

* urlsw
    urlsw extracts the URL keyword from the specified column.

  arguments
    required
      -i data input path.
      -o data output path.

    option
      -f decode the specified columns. 1 or 1,2 or 1-4.
      -m master file path. huahin-tools comes with master/SearchEngine.tsv.
         format is TSV(search engine host/search engine path/search engine query name).
      -d delimiter. Default TAB.

  For example:

    $ ./bin/huahin-tools -l -t 4 -s 2147483648 \
       -j urlsw \
       -i /tmp/input/ \
       -o /tmp/output/ \
       -f 3,5 \
       -m master/SearchEngine.tsv
