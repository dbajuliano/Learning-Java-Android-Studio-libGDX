# Learning-Java-Android-Studio-libGDX
Learning how to code

# Update
Study material from 2018

Repositories migrated to subfolders on 24-06-2023

# 07 Projects
Each per directory

# How was it migrated?

GitHub: moving repositories as a subfolder of another repository

Main repo: ```Learning-Java-Android-Studio-libGDX```

Other repos: ```jsnake, jflappybird, etc...```

Move all other repos into the main repo:
1. Create an empty main repo on GitHub: ```Learning-Java-Android-Studio-libGDX```
2. Clone it to your machine: ```git clone https://github.com/dbajuliano/Learning-Java-Android-Studio-libGDX.git```
3. To merge any other existent repo into the main repo as a subfolder, run this command inside the main repo folder:

```git subtree add -P <prefix> <repo> <rev>```

```git subtree add -P jsnake git@github.com:dbajuliano/jsnake.git HEAD```

Note: Set ```<prefix>``` to the name of the subdirectory ```jsnake```, ```<repo>``` to the clone URL of ```jsnake```, and ```<rev>``` to the revision of ```jsnake``` you want (```HEAD``` if latest)

This will take the history of the ```jsnake``` repo and merge it with ```Learning-Java-Android-Studio-libGDX```, along with an additional merge commit.

4. Push the changes to GitHub.com

```git push -u origin main```


Repeat the process for any other repo to be merged.
