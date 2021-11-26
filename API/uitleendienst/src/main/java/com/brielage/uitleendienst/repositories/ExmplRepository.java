package com.brielage.uitleendienst.repositories;

import com.brielage.uitleendienst.models.Exmpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// just to give responses for the controller, not to be used as an actual repository
public class ExmplRepository {
    private List<Exmpl> alleExmpls = new ArrayList<>();

    public ExmplRepository () {
        Exmpl one   = new Exmpl("1234-abcd-5678-efgh", "naam", "test");
        Exmpl two   = new Exmpl("5678-efgh-1234-abcd", "naam2", "test2");
        Exmpl three = new Exmpl("4321-dcba-8765-hgfe", "naam3", "test3");
        Exmpl four  = new Exmpl("8765-hgfe-4321-dcba", "naam4", "test4");
        alleExmpls.add(one);
        alleExmpls.add(two);
        alleExmpls.add(three);
        alleExmpls.add(four);
    }

    public List<Exmpl> findAll () {
        return alleExmpls;
    }

    public Optional<Exmpl> findById (String id) {
        for (Exmpl e : alleExmpls)
            if (e.getId()
                 .equals(id)) return Optional.of(e);
        return Optional.empty();
    }

    public Exmpl save (Exmpl exmpl)
            throws
            Exception {
        if (exmpl.getTest()
                 .equals("fail"))
            throw new Exception();

        if (exmpl.getId() == null ||
                exmpl.getId()
                     .isEmpty())
            exmpl.setId(generateId());

        alleExmpls.removeIf(e -> e.getId()
                                  .equals(exmpl.getId()));
        alleExmpls.add(exmpl);

        return (exmpl);
    }

    public void delete (Exmpl exmpl) {
        alleExmpls.removeIf(e -> e.getId()
                                  .equals(exmpl.getId()));
    }

    public List<Exmpl> findByNameIsIn (List<String> names) {
        List<Exmpl> list = new ArrayList<>();

        for (Exmpl e : alleExmpls)
            for (String n : names)
                if (e.getName()
                     .equals(n)) list.add(e);

        return list;
    }

    public List<Exmpl> findByTestIsIn (List<String> tests) {
        List<Exmpl> list = new ArrayList<>();

        for (Exmpl e : alleExmpls)
            for (String t : tests)
                if (e.getTest()
                     .equals(t)) list.add(e);

        return list;
    }

    private String generateId () {
        String[] allowed = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            if (i == 4 || i == 8 || i == 12) id.append("-");
            int r = (int) (Math.random() * allowed.length);
            id.append(allowed[r]);
        }

        String output = id.toString();

        for (Exmpl e : alleExmpls)
            if (e.getId()
                 .equals(output))
                output = generateId();

        return id.toString();
    }
}
