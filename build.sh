chmod u+r+x build.sh

git pull
docker exec hadoop rm -rf /usr/local/hadoop/App/
docker cp /HadoopDataAnalysis/src/App/. hadoop:/usr/local/hadoop/App/
docker exec hadoop ant -f /usr/local/hadoop/App/build_App.xml makejar