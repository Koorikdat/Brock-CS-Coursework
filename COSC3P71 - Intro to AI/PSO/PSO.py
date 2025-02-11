import random, string

class PopContainer:
    def __init__(self, key, fit_score):
        self.key = key
        self.fit_score = fit_score

    def __str__(self):
        return f"Data: Key = '{self.key}', Fitness = {self.fit_score}"

class Particle:
    def __init__(self, position, fit_score,):
        self.position = position
        self.fit_score = fit_score

    def __str__(self):
        return f"Data: Key = '{self.key}', Fitness = {self.fit_score}"



def fitnessRastrigin(key, input_text):
    # Your fitness calculation logic here
    # This is a simple example, you should replace it with your actual fitness function
    return random.uniform(0, 1)

def fitnessEggHolder(key, input_text):
    # Your fitness calculation logic here
    # This is a simple example, you should replace it with your actual fitness function
    return random.uniform(0, 1)

def fitness(key, input_text):
    # Your fitness calculation logic here
    # This is a simple example, you should replace it with your actual fitness function
    return random.uniform(0, 1)

def initialize_population(population_size, key_length):
    population = []
    for _ in range(population_size):
        key = ''.join(random.choice(string.ascii_lowercase) for _ in range(key_length))
        fit_score = fitness(key, input_text)
        population.append(PopContainer(key, fit_score))
    return population

def k_tournament_selection(population, k):
    selected_nodes = random.sample(population, k)
    selected_nodes.sort(key=lambda x: x.fit_score, reverse=True)
    return selected_nodes[0], selected_nodes[1]

def crossover(parent1, parent2):
    crossover_point = random.randint(1, len(parent1.key) - 1)
    child_key = parent1.key[:crossover_point] + parent2.key[crossover_point:]
    fit_score = fitness(child_key, input_text)
    return PopContainer(child_key, fit_score)

def mutate(individual, mutation_rate):
    mutated_key = ''.join(
        c if random.uniform(0, 1) > mutation_rate else random.choice(string.ascii_lowercase)
        for c in individual.key
    )
    fit_score = fitness(mutated_key, input_text)
    return PopContainer(mutated_key, fit_score)

def evaluate_population(population):
    total_fitness = 0
    for individual in population:
        total_fitness += individual.fit_score
    mean_fitness = total_fitness / len(population)
    return mean_fitness

# Example usage
input_text = """
    Your encrypted Vigenere text goes here
"""

key_length = 26
population_size = 10
crossover_rate = 0.8
mutation_rate = 0.4
goal_fitness = 0.7

population = initialize_population(population_size, key_length)

generation = 10
while True:
    # Evaluate fitness of all individuals
    for individual in population:
        individual.fit_score = fitness(individual.key, input_text)

    # Select subpopulation using k-tournament selection
    selected1, selected2 = k_tournament_selection(population, 2)

    # Utilize 1-point crossover
    offspring = crossover(selected1, selected2)

    # Mutate the resulting offspring
    offspring = mutate(offspring, mutation_rate)

    # Replace the least fit individual with the offspring
    population.sort(key=lambda x: x.fit_score)
    population[0] = offspring

    # Evaluate the new individuals
    mean_fitness = evaluate_population(population)

    # Print fitness values and mean fitness of each generation
    print(f"Generation {generation}: Mean Fitness = {mean_fitness}")
    for individual in population:
        print(individual)

    # Check if the goal fitness score condition is met
    if mean_fitness >= goal_fitness:
        print(f"Goal fitness score ({goal_fitness}) achieved at generation {generation} ")
        break

    generation += 1
