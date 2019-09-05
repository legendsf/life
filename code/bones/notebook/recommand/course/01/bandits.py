import numpy as np
import matplotlib.pyplot as plt
import math
number_of_bandits=10
number_of_arms=10
number_of_pulls=10000
epsilon=0.3
min_temp = 0.1
decay_rate=0.999

def pick_arm(q_values,counts,strategy,success,failure):
	global epsilon
	if strategy=="random":
		return np.random.randint(0,len(q_values))

	if strategy=="greedy":
		best_arms_value = np.max(q_values)
		best_arms = np.argwhere(q_values==best_arms_value).flatten()
		return best_arms[np.random.randint(0,len(best_arms))]

	if strategy=="egreedy" or strategy=="egreedy_decay": 
		if  strategy=="egreedy_decay": 
			epsilon=max(epsilon*decay_rate,min_temp)
		if np.random.random() > epsilon:
			best_arms_value = np.max(q_values)
			best_arms = np.argwhere(q_values==best_arms_value).flatten()
			return best_arms[np.random.randint(0,len(best_arms))]
		else:
			return np.random.randint(0,len(q_values))

	if strategy=="ucb":
		total_counts = np.sum(counts)
		q_values_ucb = q_values + np.sqrt(np.reciprocal(counts+0.001)*2*math.log(total_counts+1.0))
		best_arms_value = np.max(q_values_ucb)
		best_arms = np.argwhere(q_values_ucb==best_arms_value).flatten()
		return best_arms[np.random.randint(0,len(best_arms))]

	if strategy=="thompson":
		sample_means = np.zeros(len(counts))
		for i in range(len(counts)):
			sample_means[i]=np.random.beta(success[i]+1,failure[i]+1)
		return np.argmax(sample_means)


fig = plt.figure()
ax = fig.add_subplot(111)
for st in ["greedy","random","egreedy","egreedy_decay","ucb","thompson"]:

	best_arm_counts = np.zeros((number_of_bandits,number_of_pulls))

	for i in range(number_of_bandits):
		arm_means = np.random.rand(number_of_arms)
		best_arm = np.argmax(arm_means)

		q_values = np.zeros(number_of_arms)
		counts = np.zeros(number_of_arms)
		success=np.zeros(number_of_arms)
		failure=np.zeros(number_of_arms)

		for j in range(number_of_pulls):
			a = pick_arm(q_values,counts,st,success,failure)

			reward = np.random.binomial(1,arm_means[a])
			counts[a]+=1.0
			q_values[a]+= (reward-q_values[a])/counts[a]

			success[a]+=reward
			failure[a]+=(1-reward)
			best_arm_counts[i][j] = counts[best_arm]*100.0/(j+1)
		epsilon=0.3


	ys = np.mean(best_arm_counts,axis=0)
	xs = range(len(ys))
	ax.plot(xs, ys,label = st)

plt.xlabel('Steps')
plt.ylabel('Optimal pulls')

plt.tight_layout()
plt.legend()
plt.ylim((0,110))
plt.show()        