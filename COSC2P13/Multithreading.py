import threading
import random
import time

class main:
    def __init__(self):
        
        # intitialize the locks for each ingredient
        
        self.Tomatoes_lock = threading.Lock()
        self.Flour_lock = threading.Lock()
        self.Eggs_lock = threading.Lock()
        self.SaltMallet_lock = threading.Lock()
        self.PrepCounter_lock = threading.Lock()
        self.Stove_lock = threading.Lock()
        self.Oven_lock = threading.Lock()
        self.Sink_lock = threading.Lock()
        self.Output_lock = threading.Lock()

 # create methods for each ingredient to be used
    def use_TomatoHamper(self, dish):
        with self.Tomatoes_lock:
            print(f"Using tomatoes for {dish}")
            
    def use_Flour(self, dish):
        with self.Flour_lock:
            print(f"Using Flour for {dish}")
            
    def use_Eggs(self, dish):
        with self.Eggs_lock:
            print(f"Using Eggs for {dish}")
            
    def use_SaltMallet(self, dish):
        with self.SaltMallet_lock:
            print(f"Using SaltMallet for {dish}")       
            
    def use_PrepCounter(self, dish):
        with self.PrepCounter_lock:
            print(f"Using Prep counter for {dish}")      
            
    def use_Stove(self, dish):
        with self.Stove_lock:
            print(f"Using Stove for {dish}")

    def use_Oven(self, dish):
        with self.Oven_lock:
            print(f"Using Oven for {dish}")
                
    def use_Sink(self, dish):
        with self.Sink_lock:
            print(f"Using Sink for {dish}")
                
    def use_Output(self, dish):
        with self.Output_lock:
            print(f"Using Output for {dish}")
            print(f"{dish} is prepared!")
            
        
            
    

def dish_function(kitchen, recipe):
    
    if recipe == "pasta":
        kitchen.use_Flour(recipe)
        kitchen.use_Eggs(recipe)
        kitchen.use_Eggs(recipe)
        kitchen.use_Sink(recipe)
        kitchen.use_SaltMallet(recipe)
        kitchen.use_Stove(recipe)
        kitchen.use_Output(recipe)
    
    elif recipe == "pizza":
        kitchen.use_Flour(recipe)
        kitchen.use_PrepCounter(recipe)
        kitchen.use_TomatoHamper(recipe)
        kitchen.use_Oven(recipe)
        kitchen.use_Stove(recipe)
        kitchen.use_Output(recipe)

    elif recipe == "frozen pizza":
        kitchen.use_Oven(recipe)
        kitchen.use_Output(recipe)
        
    elif recipe == "chicken":
        kitchen.use_SaltMallet(recipe)
        kitchen.use_Eggs(recipe)
        kitchen.use_Sink(recipe)
        kitchen.use_Stove(recipe)
        kitchen.use_Output(recipe)
        


Kitchen = main()

threads = []
dishes = ["pasta", "pizza", "frozen pizza", "chicken"]

t=time.time()
for thread in threads:
    thread.join()
    t.join()


# make the recipes here with dish_function(Kitchen , "recipe)
dish_function(Kitchen, "pasta")
dish_function(Kitchen, "pizza")
dish_function(Kitchen, "frozen pizza")
dish_function(Kitchen, "chicken")