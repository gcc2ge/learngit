import random

def expmod(base,exp,m):
	if exp==0:
		return 1
	elif exp%2==0:
		return expmod(base,exp/2,m)**2 % m
	else:
		return expmod(base,exp-1,m)*base % m
def fermat_test(n):
	a=random.randint(1,n-1)
	if a==expmod(a,n,n):
		return 1
	else:
		return 0
def fermat_prime(n,time):
	if time==0:
		return 1
	elif fermat_test(n)==1:
		return fermat_prime(n,time-1)
	else:
		return 0
#print fermat_test(11)
print fermat_prime(99,100)