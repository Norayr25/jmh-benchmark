A benchmark has been created to test and compare the performance of the add () method for the HashSet and LinkedHashSet of the java.util package.
In ML RCA, we changed the HashSet to LinkedHashSet. We only need to test the performance of the add () method for the HashSet and LinkedHashSet,
because we are mainly using the add () method. The result shows that the add () method of the LinkedHashSet was slightly faster.


The current result on my Mac is as follows: 

Run Progres: 0.00% complete, ETA 00:01:20
Fork: 1 of 2
Warmup Iteration  1: 266.229 s/op
Iteration 1: 298.675 s/op
Iteration 2: 313.509 s/op
Iteration 3: 300.847 s/op

Run Progres: 50.00% complete, ETA 00:19:39
Fork: 2 of 2
Warmup Iteration  1: 265.924 s/op
Iteration 1: 309.064 s/op
Iteration 2: 297.959 s/op
Iteration 3: 373.838 s/op


Result "benchmark.PutBenchmark.Test_HashSet":
                 (min, avg, max) = (297.959, 315.649, 373.838), stdev = 29.169
		 CI (99.9%): [233.854, 397.443] (assumes normal distribution)


Run Progres: 0.00% complete, ETA 00:01:20
Fork: 1 of 2
Warmup Iteration  1: 273.846 s/op
Iteration 1: 287.398 s/op
Iteration 2: 277.472 s/op
Iteration 3: 273.477 s/op

Run Progres: 50.00% complete, ETA 00:18:32
Fork: 2 of 2
Warmup Iteration  1: 263.998 s/op
Iteration 1: 261.830 s/op
Iteration 2: 265.227 s/op
Iteration 3: 255.459 s/op


Result "benchmark.PutBenchmark.Test_LinkedHashSet":                 
                 (min, avg, max) = (255.459, 270.144, 287.398), stdev = 11.588
                 CI (99.9%): [237.648, 302.640] (assumes normal distribution)

