# Inference
A script to run rejection sampling and likelihood weighting for a Bayesian Network.
See the 'Tables and Bayes Net' file to see the distributions being sampled.

The command line input is as follows:

java Inference <sampling method> <number of samples>
  
There are a few options for input.

For likelihood weighting with 10,000 samples the input is:

java Inference like 10000

For rejection sampling with 10,000 samples the input is:

java Inference reject 10000
