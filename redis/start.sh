rm -rf ./**/dump*
rm -rf ./**/append*
rm -rf ./**/node*

for d in ./*/ ; do (cd "$d" && redis-server ./redis.conf &); done

redis-cli --cluster create 127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 --cluster-replicas 1