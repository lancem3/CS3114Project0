NUM_TESTS = 10


from threading import Timer

import subprocess
from subprocess import Popen, PIPE

try:
    st = subprocess.check_output(["javac", "-version"])
except:
    print "Unable to find javac compiler. Make sure it is added to your system path"
    exit(1)
print "Found javac compiling code..."
try:
    st = subprocess.check_output(["javac", "Project0.java"])
except: 
    print "Java code failed to compile. Ensure your main file is named Project0.java"
    exit(1)
print "Executing Java code."


for test in range(1, NUM_TESTS + 1):
    TIMEOUT = 6
    VERDICT = "ACCEPTED"
    
    st = ""
    err = ""
    
    with open('tests/test{}.in'.format(test), 'r') as in_file:
        try:

            process = Popen(["java", "-Xmx512m", "Project0"], stdin=in_file, stdout = PIPE, stderr = PIPE)
            
 
            def on_timer_finish(process):
                process.kill()
                global VERDICT
                VERDICT = "REJECTED, TIMED OUT"
 
            my_timer = Timer(TIMEOUT, on_timer_finish, [process])
 
            try:
                my_timer.start()
                st, err = process.communicate()
            finally:
                my_timer.cancel()
            
        except: 
            print "Java code encountered a runtime error or returned a non-zero exit code."
            VERDICT = "REJECTED, RUNTIME ERROR"
            
        if "java.lang.OutOfMemoryError:" in err:
            VERDICT = "REJECTED, OUT OF MEMORY"
        elif "Exception in thread" in err and "java.lang." in err:
            VERDICT = "REJECTED, RUNTIME ERROR"
    
    #Load sample correct output:
    correct_output = ""
    with open('tests/test{}.out'.format(test), 'r') as sample_file:
        correct_output = sample_file.read()

    st = st.replace('\r', '')
    st = st.replace('\t', ' ')

    #Test Correctness
    if VERDICT == "ACCEPTED":
        lines1 = [x for x in st.split("\n") if x]
        lines2 = [x for x in correct_output.split("\n") if x]
        
        if len(lines1) != len(lines2):
            pass
            VERDICT = "REJECTED, NUMBER OF NON-EMPTY LINES DIFFERS FROM SOLUTION"
        else:
            for line_num in range(len(lines1)):
                try:
                    nums1 = sorted([int(x) for x in lines1[line_num].split(" ") if x])
                    nums2 = sorted([int(x) for x in lines2[line_num].split(" ") if x])
                    if nums1 != nums2:
                        VERDICT = "REJECTED, WRONG ANSWER"
                        break
                except:
                    VERDICT = "REJECTED, EXPECTED INTEGER AND FOUND NON-INTEGER"
                    break
        
            
    print "Test {} verdict: {}".format(test, VERDICT)

