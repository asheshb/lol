package com.bitwindow;

import java.util.Random;

public class JokeServe {

    //credit: https://blog.samanage.com/friday-fun/10-jokes-that-only-help-desk-pros-would-understand/
    private final static String[] jokeItems = {

            "Q: Why do programmers always mix up Halloween and Christmas? A: Because Oct 31 = Dec 25. (Do the math. It's true!)",
            "\"Knock, knock.\"\n\n\"Who's there?\"\nExtremely long pause... \"Java.\"",
            "A programmer is sent to the grocery store with instructions to \"Buy butter and see whether they have eggs, if they do, then buy 10.\"\nReturning with 10 cartons of butter, the programmer says, \"They had eggs.\"",
            "Q: Why did the programmer quit his job?\nA: Because he didn't get arrays.",
            "Q: What is the difference between a programmer and a non-programmer?\nA: The non-programmer thinks a kilobyte is 1000 bytes while a programmer thinks that a kilometer is 1024 meters.",
            "Zen koan, or joke?: If the software specs read, \"This software requires Windows XP or better,\" does that mean it will run on Linux?",
            "Q. Why did the programmer get stuck in the shower?\nA. Because the instructions on the shampoo bottle said, \"Lather, rinse, repeat.\"",
            "An aspiring writer once came across a magical lamp, and when he rubbed it, a genie appeared and offered to grant him one wish. Being a writer, he said, \"I want to write things that the entire world will read over and over. I want to write things that will affect people enough to make them scream and cry out, so strong will be their emotional reaction.\" So the genie gave him a job writing error messages for Microsoft. (Ba-dum, KSSH!)",
            "Q. What sits on your shoulder and squawks, \"Pieces of 7! Pieces of 7!\"?\nA. A parroty error.",
            "Q. What did one programmer write on the other programmer's birthday card?\nA. [\"hip\",\"hip\"] (hip hip array, get it?)."

    };



    public static String getJoke(){
        int idx = new Random().nextInt(jokeItems.length);

        return (jokeItems[idx]);

    }
}
