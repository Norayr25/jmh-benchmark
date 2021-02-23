# jmh-benchmark for Caffein.
The purpose of this benchmark is to test and compare the performance of the following methods: `put()`, `putIfAbsent()` and `computeIfAbsent()`.
It is interesting to execute the same methods of two versions of the Caffein library: `2.7.0` and `2.8.8`.

The current result on my Mac is as follows: 
```
2.7.0
Benchmark                 Mode  Cnt  Score   Error  Units
PutBenchmark.computeTest  avgt   20  0.633 ± 0.027   s/op
PutBenchmark.putIfTest    avgt   20  0.614 ± 0.013   s/op
PutBenchmark.putTest      avgt   20  0.612 ± 0.012   s/op
```
```
2.8.8
Benchmark                 Mode  Cnt  Score   Error  Units
PutBenchmark.computeTest  avgt   20  0.628 ± 0.019   s/op
PutBenchmark.putIfTest    avgt   20  0.608 ± 0.014   s/op
PutBenchmark.putTest      avgt   20  0.606 ± 0.016   s/op
```
