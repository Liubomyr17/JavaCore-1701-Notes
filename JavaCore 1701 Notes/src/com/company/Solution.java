package com.company;

/*

1701 Notes

Asynchronous execution of threads.
1. The Note class will be used by threads.
2. Create a public static thread NoteThread (Runnable is not a thread), which in the run method 1000 times (index = 0-999) will do the following:
2.1. Using the addNote method will add a note with the name [getName () + "-Note" + index], for example, with index = 4
"Thread-0-Note4"
2.2. Falls asleep for 1 millisecond
2.3. Using the removeNote method will remove the note
2.4. Pass the name of the thread as a parameter in removeNote - getName () method

Requirements:
1. The Solution class must contain the public static NoteThread class.
2. The NoteThread class must be a thread.
3. The run method of the NoteThread class must have a loop.
4. The run method of the NoteThread class must call the addNote method 1000 times with the parameter (getName () + "-Note" + index).
5. The run method of the NoteThread class must call Thread.sleep () 1000 times with parameter (1) 1000 times.
6. The run method of the NoteThread class must call the removeNote method 1000 times with the parameter (getName ()) 1000 times.

 */

import java.util.ArrayList;
import java.util.List;

/*
Заметки
*/

public class Solution {
    public static void main(String[] args) {
        new NoteThread().start();
        new NoteThread().start();
    }

    public static class Note {

        public static final List<String> notes = new ArrayList<>();

        public static void addNote(String note) {
            notes.add(0, note);
        }

        public static void removeNote(String threadName) {
            String note = notes.remove(0);
            if (note == null) {
                System.out.println("Другая нить удалила нашу заметку");
            } else if (!note.startsWith(threadName)) {
                System.out.println("Нить [" + threadName + "] удалила чужую заметку [" + note + "]");
            } else {
                System.out.println("Нить [" + threadName + "] удалила свою заметку [" + note + "]");
            }
        }
    }

    public static class NoteThread extends Thread {

        public void run() {
            try {
                for (int index = 0; index < 1000; index++) {
                    Note.addNote(getName() + "-Note" + index);
                    Thread.sleep(1);
                    Note.removeNote(getName());
                }
            } catch (InterruptedException e) {

            }
        }
    }

}




