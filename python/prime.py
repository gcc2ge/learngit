def prime(n):
	for i in range(1,n+1):
		if i==1:
			print 1,
		elif i==2:
			print 2,
		elif testprime(i,2)==1:
			print i,
def testprime(n,t):
	if t ** 2>n:
		return 1
	elif n%t==0:
		return 0
	else:
		return testprime(n,t+1)
prime(1000000)