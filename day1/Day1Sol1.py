def fuelRequired(mass):
	return mass // 3 - 2

#======
totalFuelRequired = 0
with open('./Day1_input1.txt','r') as masses:
#with open('./testDataDay1.txt','r') as masses:
	for _,module_mass in  enumerate(masses):
		totalFuelRequired += fuelRequired(int(module_mass))
print(totalFuelRequired)

