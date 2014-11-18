#10 5 2 1
kinds=[1,5,10,25,50]
##cache the result
resultDic={}
##cache hit counts
hitDic={}

def charge(m,k):
	if resultDic.get(str(m)+'-'+str(k))==None:
		hitDic[str(m)+'-'+str(k)]=0
	else:
		hitDic[str(m)+'-'+str(k)]=hitDic[str(m)+'-'+str(k)]+1
		return resultDic[str(m)+'-'+str(k)]
	if m==0:
		return 1
	elif m<0 or k<0:
		return 0
	else:
		resultDic[str(m)+'-'+str(k)]=charge(m,k-1)+charge(m-kinds[k],k)
		return resultDic[str(m)+'-'+str(k)]



