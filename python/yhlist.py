def f(n):
	for i in range(n):
		if i<2:
			yhlist=[1]*(1+i)
		else:
			yhlist[1:-1]= [ yhlist[i]+value for i,value in enumerate(yhlist[1:]) ]
		print yhlist
f(15)