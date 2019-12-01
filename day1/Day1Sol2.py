def fuelRequired(mass):
	fuel = mass // 3 - 2
	return fuel if fuel > 0 else 0
#======
totalFuelRequired = 0
with open('./Day1_input1.txt','r') as masses:
#with open('./testDataDay1.txt','r') as masses:
	for _,module_mass in  enumerate(masses):
		moduleFuel = fuelRequired(int(module_mass))
		totalFuelRequired += moduleFuel
		while moduleFuel:
			moduleFuel = fuelRequired(moduleFuel)
			totalFuelRequired += moduleFuel
print(totalFuelRequired)

