'''
def f(n):
	if n<3:
		return n
	else:
		f(n-1)+2*f(n-2)+3*f(n-3)
'''
def f(n):
	if n==1:
		return 1
	elif n==2:
		return 2
	elif n==3:
		return 3
	else:#>=4
		return f_iter(1,2,3,3,n)
def f_iter(v1,v2,v3,index,n):
	if index==n:
		return v3
	else:
		f_iter(v2,v3,v1+v2+v3,index+1,n)
print f(7)
'''
f(1)=1
f(2)=2
f(3)=3
f(4)=3+2+1=6
f(5)=6+3+2=11
f(6)=11+6+3=20
f(7)=20+11+6=37
f(4)=f(3)+f(2)+f(1)
f(5)=f(4)+f(3)+f(2)
'''


