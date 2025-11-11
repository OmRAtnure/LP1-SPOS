import java.util.*;

public class PASS1 {
    // Symbol Table
    static class Symbol {
        String name;
        int address;

        Symbol(String n, int a) {
            name = n;
            address = a;
        }
    }

    // Literal Table
    static class Literal {
        String value;
        int address;

        Literal(String v, int a) {
            value = v;
            address = a;
        }
    }

    static ArrayList<Symbol> symtab = new ArrayList<>();
    static ArrayList<Literal> littab = new ArrayList<>();
    static ArrayList<Integer> pooltab = new ArrayList<>();
    static ArrayList<String> intermediate = new ArrayList<>();

    static int LC = 0; // Location counter
    static int litStart = 0; // start index of literal pool

    public static void main(String[] args) {
        String[] code = {
                "START 100",
                "MOVR AX 05",
                "MOVER BX 10",
                "UP: ADD AX BX",
                "MOVEM A ='5'",
                "ORIGIN UP",
                "LTORG",
                "MOVEM B ='7'",
                "DS A 02",
                "DC B 10",
                "END"
        };

        for (String line : code) {
            process(line.trim());
        }

        printTables();
    }

    static void process(String line) {
        if (line.isEmpty()) return;

        String[] parts = line.split("\\s+");
        int index = 0;

        // Handle label
        if (parts[0].endsWith(":")) {
            String label = parts[0].substring(0, parts[0].length() - 1);
            symtab.add(new Symbol(label, LC));
            index = 1;
        }

        if (index >= parts.length) return;
        String opcode = parts[index].toUpperCase();

        switch (opcode) {
            case "START":
                LC = Integer.parseInt(parts[index + 1]);
                intermediate.add("(AD,01) (C," + LC + ")");
                break;

            case "END":
                handleLTORG();
                intermediate.add("(AD,02)");
                break;

            case "ORIGIN":
                String sym = parts[index + 1];
                for (Symbol s : symtab) {
                    if (s.name.equals(sym)) {
                        LC = s.address;
                        break;
                    }
                }
                intermediate.add("(AD,03) (S," + sym + ")");
                break;

            case "LTORG":
                handleLTORG();
                intermediate.add("(AD,05)");
                break;

            case "DS":
            case "DC":
                String var = parts[index + 1];
                int val = Integer.parseInt(parts[index + 2]);
                symtab.add(new Symbol(var, LC));
                intermediate.add("(" + (opcode.equals("DS") ? "DL,02" : "DL,01") + ") (S," + var + ")(C," + val + ")");
                LC++;
                break;

            default:
                // IS instruction
                String code = "(IS)";
                String ops = "";

                for (int i = index + 1; i < parts.length; i++) {
                    String op = parts[i];
                    if (op.matches("[A-Za-z]+")) { // symbol
                        symtab.add(new Symbol(op, -1));
                        ops += " (S," + op + ")";
                    } else if (op.startsWith("='")) { // literal
                        littab.add(new Literal(op, -1));
                        ops += " (L," + littab.size() + ")";
                    } else {
                        ops += " (C," + op + ")";
                    }
                }

                intermediate.add(code + ops);
                LC++;
                break;
        }
    }

    static void handleLTORG() {
        pooltab.add(litStart + 1);
        for (int i = litStart; i < littab.size(); i++) {
            littab.get(i).address = LC++;
        }
        litStart = littab.size();
    }

    static void printTables() {
        System.out.println("\n--- SYMBOL TABLE ---");
        for (Symbol s : symtab) {
            System.out.println(s.name + "\t" + s.address);
        }

        System.out.println("\n--- LITERAL TABLE ---");
        int i = 1;
        for (Literal l : littab) {
            System.out.println(i++ + "\t" + l.value + "\t" + l.address);
        }

        System.out.println("\n--- POOL TABLE ---");
        i = 1;
        for (int pool : pooltab) {
            System.out.println(i++ + "\t" + pool);
        }

        System.out.println("\n--- INTERMEDIATE CODE ---");
        for (String s : intermediate) {
            System.out.println(s);
        }
    }
}
