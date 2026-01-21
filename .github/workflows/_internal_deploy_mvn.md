
- bitbankcc/mvn-repoの初期作成の仕方
```sh
# initial
git remote add java-bitbankcc https://github.com/bitbankinc/java-bitbankcc.git
git fetch java-bitbankcc
git checkout origin/master
git checkout mvn-repo
git merge public/mvn-repo --allow-unrelated-histories
git push origin mvn-repo
```