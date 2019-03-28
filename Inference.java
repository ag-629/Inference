import java.util.Currency;
import java.util.Random;

public class Inference {

	public void runReject(int numSamples) {

		System.out.println("The theoretical value for P(Rain) is 0.2. Using rejection sampling with " + numSamples
				+ " samples: " + rejectionSamplingRain(numSamples));
		System.out.println("The theoretical value for P(Tired|~Happy) is 0.377. Using rejection sampling with "
				+ numSamples + " samples: " + rejectionSamplingTiredNotHappy(numSamples));
		System.out.println("The theoretical value for P(Happy | Sunny) is 0.640. Using rejection sampling with "
				+ numSamples + " samples: " + rejectionSamplingHappySunny(numSamples));
		System.out.println("The theoretical value for P(Snow | Happy, ~Tired) is 0.163. Using rejection sampling with "
				+ numSamples + " samples: " + rejectionSamplingSnowHappyNotTired(numSamples));
	}

	public double rejectionSamplingRain(int numSamples) {
		Random r = new Random();
		double successes = 0;
		for (int i = 0; i < numSamples; i = i + 1) {
			double sample = r.nextDouble();
			// System.out.println(sample);
			if (sample < 0.2) {
				successes = successes + 1;
			}
		}
		double result = successes / numSamples;
		result = Math.round(result * 1000);
		result = result / 1000;
		return result;
	}

	public double rejectionSamplingTiredNotHappy(int numSamples) {
		double tiredCount = 0.0;
		double notHappyCount = 0.0;
		Random r = new Random();
		for (int i = 0; i < numSamples; i = i + 1) {
			// Get a weather sample first
			double weatherSample = r.nextDouble();
			// Weather = Sunny
			if (weatherSample < 0.4) {
				// Sample tired
				double tiredSample = r.nextDouble();
				if (tiredSample < 0.3) {
					double notHappySample = r.nextDouble();
					// tired and not happy
					if (notHappySample < 0.5) {
						tiredCount = tiredCount + 1;
						notHappyCount = notHappyCount + 1;
					}
				}
				if (tiredSample >= 0.3) {
					// not tired not happy
					double notHappySample = r.nextDouble();
					if (notHappySample < 0.3) {
						notHappyCount = notHappyCount + 1;
					}
				}
			}
			// Weather = Cloudy
			if (weatherSample >= 0.4 && weatherSample < 0.7) {
				// Sample tired
				double tiredSample = r.nextDouble();
				if (tiredSample < 0.3) {
					double notHappySample = r.nextDouble();
					// tired and not happy
					if (notHappySample < 0.9) {
						tiredCount = tiredCount + 1;
						notHappyCount = notHappyCount + 1;
					}
				}
				if (tiredSample >= 0.3) {
					// not tired not happy
					double notHappySample = r.nextDouble();
					if (notHappySample < 0.7) {
						notHappyCount = notHappyCount + 1;
					}
				}
			}
			// Weather = Rain
			if (weatherSample >= 0.7 && weatherSample < 0.9) {
				// Sample tired
				double tiredSample = r.nextDouble();
				if (tiredSample < 0.3) {
					double notHappySample = r.nextDouble();
					// tired and not happy
					if (notHappySample < 0.9) {
						tiredCount = tiredCount + 1;
						notHappyCount = notHappyCount + 1;
					}
				}
				if (tiredSample > 0.3) {
					// not tired not happy
					double notHappySample = r.nextDouble();
					if (notHappySample < 0.8) {
						notHappyCount = notHappyCount + 1;
					}
				}
			}
			// Weather = Snow
			if (weatherSample >= 0.9 && weatherSample < 1.0) {
				// Sample tired
				double tiredSample = r.nextDouble();
				if (tiredSample < 0.3) {
					double notHappySample = r.nextDouble();
					// tired and not happy
					if (notHappySample < 0.4) {
						tiredCount = tiredCount + 1;
						notHappyCount = notHappyCount + 1;
					}
				}
				if (tiredSample >= 0.3) {
					// not tired not happy
					double notHappySample = r.nextDouble();
					if (notHappySample < 0.2) {
						notHappyCount = notHappyCount + 1;
					}
				}
			}
		}
		if (notHappyCount > 0) {
			double result = tiredCount / notHappyCount;
			result = Math.round(result * 1000);
			result = result / 1000;
			return result;
		} else {
			return 0.0;
		}
	}

	public double rejectionSamplingHappySunny(int numSamples) {
		double sunnyCount = 0.0;
		double happyCount = 0.0;
		Random r = new Random();
		// Sample Weather then Happy
		for (int i = 0; i < numSamples; i = i + 1) {
			double weatherSample = r.nextDouble();
			// Weather = Sunny
			if (weatherSample < 0.4) {
				sunnyCount = sunnyCount + 1;
				double tiredSample = r.nextDouble();
				// Next, sample Tired
				if (tiredSample < 0.3) {
					double happySample = r.nextDouble();
					if (happySample < 0.5) {
						happyCount = happyCount + 1;
					}
				} else {
					double happySample = r.nextDouble();
					if (happySample < 0.7) {
						happyCount = happyCount + 1;
					}
				}
			}
		}
		if (sunnyCount > 0) {
			double result = happyCount / sunnyCount;
			result = Math.round(result * 1000);
			result = result / 1000;
			return result;
		} else {
			return 0.0;
		}
	}

	public double rejectionSamplingSnowHappyNotTired(int numSamples) {
		double snowCount = 0.0;
		double notTiredHappyCount = 0.0;
		Random r = new Random();
		for (int i = 0; i < numSamples; i = i + 1) {
			double notTiredSample = r.nextDouble();
			if (notTiredSample < 0.7) {
				double weatherSample = r.nextDouble();
				// Weather = Sunny
				if (weatherSample < 0.4) {
					double happySample = r.nextDouble();
					if (happySample < 0.7) {
						notTiredHappyCount = notTiredHappyCount + 1;
					}
				}
				// Weather = Cloudy
				else if (weatherSample >= 0.4 && weatherSample < 0.7) {
					double happySample = r.nextDouble();
					if (happySample < 0.3) {
						notTiredHappyCount = notTiredHappyCount + 1;
					}
				}
				// Weather = Rain
				else if (weatherSample >= 0.7 && weatherSample < 0.9) {
					double happySample = r.nextDouble();
					if (happySample < 0.2) {
						notTiredHappyCount = notTiredHappyCount + 1;
					}
				}
				// Weather = Snow
				else {
					double happySample = r.nextDouble();
					if (happySample < 0.8) {
						snowCount = snowCount + 1;
						notTiredHappyCount = notTiredHappyCount + 1;
					}
				}
			}
		}
		if (notTiredHappyCount > 0) {
			double result = snowCount / notTiredHappyCount;
			result = Math.round(result * 1000);
			result = result / 1000;
			return result;
		} else {
			return 0.0;
		}
	}

	public void runLikelihood(int numSamples) {
		System.out.println("Theoretical value for P(Rain) is 0.2. Using likelihood weighting with " + numSamples
				+ " samples: " + likelihoodSamplingRain(numSamples));
		System.out.println("Theoretical value for P(Tired | ~Happy) is 0.377. Using likelihood weighting with "
				+ numSamples + " samples: " + likelihoodSamplingTiredNotHappy(numSamples));
		System.out.println("Theoretical value for P(Happy | Sunny) is 0.640. Using likelihood weighting with "
				+ numSamples + " samples: " + likelihoodSamplingHappySunny(numSamples));
		System.out.println("Theoretical value for P(Snow | ~Happy, Tired) is 0.163. Using likelihood weighting with "
				+ numSamples + " samples: " + likelihoodSamplingSnowHappyNotTired(numSamples));
	}

	public double likelihoodSamplingRain(int numSamples) {
		double weightOfAllSamplesTotal = numSamples * 1.0;
		double weightOfSamplesWithRainTotal = 0.0;
		Random r = new Random();
		for(int i = 0; i < numSamples; i = i + 1) {
			//There's no evidence variable so the weight of samples is 1.0
			double weatherSample = r.nextDouble();
			//P(Rain) = 0.2
			if(weatherSample < 0.2) {
				weightOfSamplesWithRainTotal = weightOfSamplesWithRainTotal + 1.0;
			}
		}
		if (weightOfAllSamplesTotal > 0) {
			double result = weightOfSamplesWithRainTotal / weightOfAllSamplesTotal;
			result = Math.round(result * 1000);
			result = result / 1000;
			return result;
		} else {
			return 0.0;
		}
	}

	public double likelihoodSamplingTiredNotHappy(int numSamples) {
		// P(T|~H)
		// For each sample force the variable happy to be false
		// Even though happy cannot be sampled on its own, still have to sample weather
		// then tired
		// The result id the number of samples that have (T,~H) divided by all samples
		// where Happy = False
		Random r = new Random();
		double tiredAndNotHappyTotalWeight = 0.0;
		double notHappyTotalWeight = 0.0;
		for (int i = 0; i < numSamples; i = i + 1) {
			double currentSampleWeight = 1.0;
			double weatherSample = r.nextDouble();
			// Weather = Sunny
			if (weatherSample < 0.4) {
				double tiredSamaple = r.nextDouble();
				if (tiredSamaple < 0.3) {
					// tired and not happy, update both
					currentSampleWeight = currentSampleWeight * 0.5;
					tiredAndNotHappyTotalWeight = tiredAndNotHappyTotalWeight + currentSampleWeight;
					notHappyTotalWeight = notHappyTotalWeight + currentSampleWeight;

				} else {
					// not tired and not happy, update only not happy
					currentSampleWeight = currentSampleWeight * 0.3;
					notHappyTotalWeight = notHappyTotalWeight + currentSampleWeight;
				}
			}
			// Weather = Cloudy
			if (weatherSample >= 0.4 && weatherSample < 0.7) {
				double tiredSamaple = r.nextDouble();
				if (tiredSamaple < 0.3) {
					// tired and not happy
					currentSampleWeight = currentSampleWeight * 0.9;
					tiredAndNotHappyTotalWeight = tiredAndNotHappyTotalWeight + currentSampleWeight;
					notHappyTotalWeight = notHappyTotalWeight + currentSampleWeight;
				} else {
					// not tired and not happy
					currentSampleWeight = currentSampleWeight * 0.7;
					notHappyTotalWeight = notHappyTotalWeight + currentSampleWeight;
				}
			}
			// Weather = Rain
			if (weatherSample >= 0.7 && weatherSample < 0.9) {
				double tiredSamaple = r.nextDouble();
				if (tiredSamaple < 0.3) {
					// tired and not happy
					currentSampleWeight = currentSampleWeight * 0.9;
					tiredAndNotHappyTotalWeight = tiredAndNotHappyTotalWeight + currentSampleWeight;
					notHappyTotalWeight = notHappyTotalWeight + currentSampleWeight;
				} else {
					// not tired and not happy
					currentSampleWeight = currentSampleWeight * 0.8;
					notHappyTotalWeight = notHappyTotalWeight + currentSampleWeight;
				}
			}
			// Weather = Snow
			if (weatherSample >= 0.9 && weatherSample < 1.0) {
				double tiredSamaple = r.nextDouble();
				if (tiredSamaple < 0.3) {
					// tired and not happy
					currentSampleWeight = currentSampleWeight * 0.4;
					tiredAndNotHappyTotalWeight = tiredAndNotHappyTotalWeight + currentSampleWeight;
					notHappyTotalWeight = notHappyTotalWeight + currentSampleWeight;
				} else {
					// not tired and not happy
					currentSampleWeight = currentSampleWeight * 0.2;
					notHappyTotalWeight = notHappyTotalWeight + currentSampleWeight;
				}
			}
		}
		if (notHappyTotalWeight > 0) {
			double result = tiredAndNotHappyTotalWeight / notHappyTotalWeight;
			result = Math.round(result * 1000);
			result = result / 1000;
			return result;
		} else {
			return 0.0;
		}
	}

	public double likelihoodSamplingHappySunny(int numSamples) {
		// Only continue if the weather Sample is < 0.4
		// Then get sample for tired
		// if tired = true happy < 0.5
		// if ~tired happy = 0.3
		double sunnyWeightsTotal = 0.0;
		double happyWeightsTotal = 0.0;
		Random r = new Random();
		for (int i = 0; i < numSamples; i = i + 1) {
			double currentSampleWeight = 1.0;
			double weatherSample = r.nextDouble();
			if (weatherSample < 0.4) {
				currentSampleWeight = currentSampleWeight * 0.4;
				sunnyWeightsTotal = sunnyWeightsTotal + currentSampleWeight;
				double tiredSample = r.nextDouble();
				if (tiredSample < 0.3) {
					double happySample = r.nextDouble();
					if (happySample < 0.5) {
						happyWeightsTotal = happyWeightsTotal + currentSampleWeight;
					}
				} else {
					double happySample = r.nextDouble();
					if (happySample < 0.7) {
						happyWeightsTotal = happyWeightsTotal + currentSampleWeight;
					}
				}
			}
		}
		if (sunnyWeightsTotal > 0) {
			double result = happyWeightsTotal / sunnyWeightsTotal;
			result = Math.round(result * 1000);
			result = result / 1000;
			return result;
		} else {
			return 0.0;
		}
	}

	public double likelihoodSamplingSnowHappyNotTired(int numSamples) {
		double happyNotTiredWeightTotal = 0.0;
		double snowHappyNotTiredWeightTotal = 0.0;
		Random r = new Random();
		for (int i = 0; i < numSamples; i = i + 1) {
			double currentSampleWeight = 1.0;
			double weatherSample = r.nextDouble();
			// Weather = Sunny
			if (weatherSample < 0.4) {
				// P(Sunny | H, ~T) = 0.7
				currentSampleWeight = currentSampleWeight * 0.7;
				happyNotTiredWeightTotal = happyNotTiredWeightTotal + currentSampleWeight;
			}
			// Weather = Cloudy
			if (weatherSample >= 0.4 && weatherSample < 0.7) {
				// P(Cloudy | H, ~T) = 0.3
				currentSampleWeight = currentSampleWeight * 0.3;
				happyNotTiredWeightTotal = happyNotTiredWeightTotal + currentSampleWeight;
			}
			// Weather = Rain
			if (weatherSample >= 0.7 && weatherSample < 0.9) {
				// P(Rain | H, ~T) = 0.2
				currentSampleWeight = currentSampleWeight * 0.2;
				happyNotTiredWeightTotal = happyNotTiredWeightTotal + currentSampleWeight;
			}
			// Weather = Snow
			if (weatherSample >= 0.9 && weatherSample < 1.0) {
				// P(Snow | H, ~T) = 0.8
				currentSampleWeight = currentSampleWeight * 0.8;
				// Update snow happy not tired counter
				snowHappyNotTiredWeightTotal = snowHappyNotTiredWeightTotal + currentSampleWeight;
				happyNotTiredWeightTotal = happyNotTiredWeightTotal + currentSampleWeight;
			}
		}
		if (happyNotTiredWeightTotal > 0) {
			double result = snowHappyNotTiredWeightTotal / happyNotTiredWeightTotal;
			result = Math.round(result * 1000);
			result = result / 1000;
			return result;
		} else {
			return 0.0;
		}
	}

	public static void main(String[] args) throws ImproperInputException {
		try {
			if (args.length != 2) {
				throw new ImproperInputException("\nINCORRECT COMMAND LINE ARGUMENTS\n"
						+ "Correct format is: java Inference <sample type: either reject or like> <integer number of samples>\n"
						+ "For example: java Inference reject 10000");
			}
			String sampleMethod = args[0];
			int numSamples = Integer.parseInt(args[1]);
			if (sampleMethod.equalsIgnoreCase("reject")) {
				new Inference().runReject(numSamples);
			} else if (sampleMethod.equalsIgnoreCase("like")) {
				new Inference().runLikelihood(numSamples);
			} else {
				throw new ImproperInputException("\nINCORRECT COMMAND LINE ARGUMENTS\n"
						+ "Correct format is: java Inference <sample type: either 'reject' or 'like'> <integer number of samples>\n"
						+ "For example: java Inference like 1000");
			}
		} catch (NumberFormatException e) {
			throw new ImproperInputException("\nINCORRECT COMMAND LINE ARGUMENTS\n"
					+ "Correct format is: java Inference <sample type: either 'reject' or 'like'> <integer number of samples>\n"
					+ "For example: java Inference reject 100");
		}
	}
}
