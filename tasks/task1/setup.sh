
function setup_app {
  mkdir ~/app$1
  cd ~/app$1
  echo "Downloading app $1"
  wget https://rawgit.com/rgrisha/secureprog/master/tasks/task1/apps/app$1
}

cd ~

for i in `seq 1 4`; do
  setup_app $i
done

