# This is the Makefile to use for this dots and boxes game
# To use, at the prompt, type:
#
# 	make		# This will make everything
# or
# 	make clean	# This will safely remove old stuff
# or
# 	make new	# This will remove all old stuff and will compile fresh

.PHONY: backup clean new
.SUFFIXES: .java .class

.java.class:
	javac -g $<

Driver: Driver.java Board.java Computer.java
	javac Driver.java
	echo 'java Driver $$*' > Driver
	chmod ug+rx Driver

clean:
	rm -f *.class

new:
	make clean
	make
