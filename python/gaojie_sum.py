'''
f(n)=1.0/n(n+2)
∑f(n)=f(1)+...f(n)
next
'''
#####################递归
def sum(f,a,next,b):
	if a>b:
		return 0
	else:
		return f(a)+sum(f,next(a),next,b)
##迭代
def sum_iter(a,index,result):
	if a>index:
		return result
	else:
		return sum_iter(next(a),index,f(a)+result)
def f(a):
	return 1.0/(a*(a+2))
def next(a):
	return a+4
print 8*sum(f,1,next,1000)
print 8*sum_iter(1,1000,0)