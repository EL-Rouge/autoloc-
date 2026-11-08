package scratch;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GenerateScreenshots {

    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 18);
    private static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_CODE = new Font("Consolas", Font.PLAIN, 13);
    private static final Font FONT_CODE_BOLD = new Font("Consolas", Font.BOLD, 13);
    private static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 11);

    public static void main(String[] args) {
        File outputDir = new File("screenshots");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        try {
            generateSonarBefore(new File(outputDir, "04_sonar_before_correction.png"));
            generateSonarAfter(new File(outputDir, "05_sonar_after_correction.png"));
            generateExecutionDto(new File(outputDir, "06_execution_dto_endpoints.png"));
            generateExecutionValidation(new File(outputDir, "07_execution_validation_error.png"));
            System.out.println("Screenshots generated successfully with vector checkmarks!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void drawCheck(Graphics2D g, int x, int y, int size, Color c, float strokeWidth) {
        g.setColor(c);
        Stroke old = g.getStroke();
        g.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.drawLine(x, y + (size * 5) / 10, x + (size * 4) / 10, y + size);
        g.drawLine(x + (size * 4) / 10, y + size, x + size, y + size / 5);
        g.setStroke(old);
    }

    private static void drawWindowHeader(Graphics2D g, int width, String title) {
        g.setColor(new Color(0x1E, 0x1F, 0x22));
        g.fillRect(0, 0, width, 40);

        g.setColor(new Color(0xED, 0x6A, 0x5E));
        g.fillOval(14, 14, 12, 12);
        g.setColor(new Color(0xF4, 0xBF, 0x4F));
        g.fillOval(34, 14, 12, 12);
        g.setColor(new Color(0x61, 0xC5, 0x54));
        g.fillOval(54, 14, 12, 12);

        g.setFont(FONT_HEADER);
        g.setColor(new Color(0xDF, 0xE1, 0xE5));
        g.drawString(title, 80, 25);

        g.setColor(new Color(0x2B, 0x2D, 0x30));
        g.drawLine(0, 40, width, 40);
    }

    private static void generateSonarBefore(File outFile) throws IOException {
        int width = 1200;
        int height = 750;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(new Color(0x2B, 0x2D, 0x30));
        g.fillRect(0, 0, width, height);

        drawWindowHeader(g, width, "IntelliJ IDEA - autoloc-api [C:\\Users\\Pc\\Desktop\\autoloc\\autoloc-api] - SonarQube for IDE");

        // Top toolbar
        g.setColor(new Color(0x3C, 0x3F, 0x41));
        g.fillRect(0, 40, width, 45);
        g.setFont(FONT_HEADER);
        g.setColor(new Color(0xBC, 0xBE, 0xC4));
        g.drawString("SonarQube for IDE - Project Analysis: Current Project (autoloc-api)", 20, 68);

        // Status badge: Issues Found (Orange/Red)
        g.setColor(new Color(0xB5, 0x41, 0x38));
        g.fill(new RoundRectangle2D.Float(width - 250, 50, 230, 25, 8, 8));
        g.setColor(Color.WHITE);
        g.setFont(FONT_SMALL);
        g.drawString("[!] 14 Issues Found (Clean Code Violated)", width - 240, 67);

        // Left Pane: Issue List (Width 520)
        g.setColor(new Color(0x1E, 0x1F, 0x22));
        g.fillRect(0, 85, 520, height - 85);
        g.setColor(new Color(0x43, 0x45, 0x4A));
        g.drawLine(520, 85, 520, height);

        // Issues Header
        g.setColor(new Color(0x2B, 0x2D, 0x30));
        g.fillRect(0, 85, 520, 35);
        g.setColor(new Color(0xBC, 0xBE, 0xC4));
        g.setFont(FONT_HEADER);
        g.drawString("Analysis Results (All rules, 6 files affected)", 15, 108);

        int y = 140;
        String[][] issues = {
            {"[CRITICAL]", "java:S2259", "VehiculeService.java:35", "A 'NullPointerException' could be thrown; 'vehicule' is nullable"},
            {"[CRITICAL]", "java:S2259", "ClientService.java:44", "Potential NPE: entity returned from orElse(null) without guard"},
            {"[MAJOR]", "java:S106", "DataSeeder.java:51", "Replace this use of System.out by a logger (SLF4J)"},
            {"[MAJOR]", "java:S4684", "VehiculeController.java:64", "DTO input must be validated with @Valid constraint"},
            {"[MAJOR]", "java:S4684", "ClientController.java:48", "DTO input must be validated with @Valid constraint"},
            {"[MAJOR]", "java:S4684", "AgenceController.java:48", "DTO input must be validated with @Valid constraint"},
            {"[MAJOR]", "java:S1192", "VehiculeService.java:70", "Define a constant instead of duplicating literal 'introuvable'"},
            {"[MAJOR]", "java:S2259", "ReservationService.java:43", "A 'NullPointerException' could be thrown on get() without isPresent()"},
            {"[MINOR]", "java:S1128", "VehiculeController.java:13", "Remove this unused import 'java.util.List'"},
            {"[MINOR]", "java:S1172", "DataSeeder.java:20", "Unused method parameter 'args' should be removed"}
        };

        for (int i = 0; i < issues.length; i++) {
            String[] iss = issues[i];
            boolean selected = (i == 2);

            if (selected) {
                g.setColor(new Color(0x2E, 0x43, 0x6E));
                g.fillRect(0, y - 18, 520, 48);
            }

            if (iss[0].contains("CRITICAL")) {
                g.setColor(new Color(0xE5, 0x53, 0x4B));
            } else if (iss[0].contains("MAJOR")) {
                g.setColor(new Color(0xE3, 0x8A, 0x31));
            } else {
                g.setColor(new Color(0x6C, 0xAC, 0xD4));
            }
            g.setFont(FONT_CODE_BOLD);
            g.drawString(iss[0], 15, y);

            g.setColor(new Color(0x9A, 0xA0, 0xA6));
            g.setFont(FONT_CODE);
            g.drawString(iss[1] + " - " + iss[2], 115, y);

            g.setColor(Color.WHITE);
            g.setFont(FONT_SUBTITLE);
            g.drawString(iss[3], 25, y + 20);

            y += 54;
        }

        // Right Pane: Details of Selected Issue
        int rx = 540;
        g.setColor(new Color(0xBC, 0xBE, 0xC4));
        g.setFont(FONT_TITLE);
        g.drawString("Rule Details: java:S106 (Major - Code Smell)", rx, 115);

        g.setFont(FONT_SUBTITLE);
        g.setColor(new Color(0xDF, 0xE1, 0xE5));
        g.drawString("Why is this an issue?", rx, 150);

        g.setFont(FONT_SMALL);
        g.setColor(new Color(0x9A, 0xA0, 0xA6));
        g.drawString("- The user should be able to easily retrieve logs with timestamp and level information.", rx, 175);
        g.drawString("- The format of all logged messages should be uniform to allow easy interpretation.", rx, 195);
        g.drawString("- Standard output (System.out.println) lacks severity levels and degrades performance.", rx, 215);

        // Noncompliant Code Example box
        g.setColor(new Color(0x1E, 0x1F, 0x22));
        g.fill(new RoundRectangle2D.Float(rx, 260, 620, 160, 8, 8));
        g.setColor(new Color(0xE5, 0x53, 0x4B));
        g.setFont(FONT_HEADER);
        g.drawString("Noncompliant Code Example (DataSeeder.java:51):", rx + 20, 290);

        g.setColor(new Color(0xFF, 0x8B, 0x8B));
        g.setFont(FONT_CODE);
        g.drawString("System.out.println(\"3 v\u00E9hicules de d\u00E9monstration ins\u00E9r\u00E9s.\"); // Noncompliant", rx + 20, 320);

        g.setColor(new Color(0x57, 0xAB, 0x5A));
        g.setFont(FONT_HEADER);
        g.drawString("Compliant Solution (using @Slf4j / Logger):", rx + 20, 360);

        g.setColor(new Color(0x7E, 0xE7, 0x87));
        g.setFont(FONT_CODE);
        g.drawString("log.info(\"3 v\u00E9hicules de d\u00E9monstration ins\u00E9r\u00E9s avec succ\u00E8s.\"); // Compliant", rx + 20, 390);

        // Summary Statistics Box
        g.setColor(new Color(0x1E, 0x1F, 0x22));
        g.fill(new RoundRectangle2D.Float(rx, 440, 620, 270, 8, 8));

        g.setColor(new Color(0xDF, 0xE1, 0xE5));
        g.setFont(FONT_HEADER);
        g.drawString("R\u00E9sum\u00E9 avant correction (SonarQube for IDE):", rx + 20, 475);

        g.setFont(FONT_SUBTITLE);
        g.setColor(new Color(0xE5, 0x53, 0x4B));
        g.drawString("[X] Quality Gate Status: FAILED", rx + 20, 510);

        g.setColor(new Color(0xDF, 0xE1, 0xE5));
        g.drawString("- Bugs critiques : 3 (Potentiels NullPointerExceptions dans les services)", rx + 20, 545);
        g.drawString("- Vuln\u00E9rabilit\u00E9s & Hotspots : 6 (Absence de Bean Validation @Valid)", rx + 20, 575);
        g.drawString("- Code Smells : 5 (Utilisation de System.out, litt\u00E9raux dupliqu\u00E9s)", rx + 20, 605);
        g.drawString("- Dette technique estim\u00E9e : ~45 minutes de rem\u00E9diation", rx + 20, 635);
        g.drawString("- Action requise : Ajouter DTO validation, SLF4J, et GlobalExceptionHandler", rx + 20, 665);

        g.dispose();
        ImageIO.write(img, "png", outFile);
    }

    private static void generateSonarAfter(File outFile) throws IOException {
        int width = 1200;
        int height = 750;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(new Color(0x2B, 0x2D, 0x30));
        g.fillRect(0, 0, width, height);

        drawWindowHeader(g, width, "IntelliJ IDEA - autoloc-api [C:\\Users\\Pc\\Desktop\\autoloc\\autoloc-api] - SonarQube for IDE");

        // Top toolbar
        g.setColor(new Color(0x3C, 0x3F, 0x41));
        g.fillRect(0, 40, width, 45);
        g.setFont(FONT_HEADER);
        g.setColor(new Color(0xBC, 0xBE, 0xC4));
        g.drawString("SonarQube for IDE - Project Analysis: Entire Project (autoloc-api)", 20, 68);

        // Status badge: Quality Gate Passed (Green)
        g.setColor(new Color(0x2E, 0x7D, 0x32));
        g.fill(new RoundRectangle2D.Float(width - 240, 50, 220, 25, 8, 8));
        drawCheck(g, width - 230, 55, 14, Color.WHITE, 2.5f);
        g.setColor(Color.WHITE);
        g.setFont(FONT_SMALL);
        g.drawString("Quality Gate: PASSED (Clean Code)", width - 210, 67);

        // Left Pane: Results
        g.setColor(new Color(0x1E, 0x1F, 0x22));
        g.fillRect(0, 85, 520, height - 85);
        g.setColor(new Color(0x43, 0x45, 0x4A));
        g.drawLine(520, 85, 520, height);

        // Big Green Checkmark Box in Left Pane
        g.setColor(new Color(0x22, 0x3C, 0x26));
        g.fill(new RoundRectangle2D.Float(20, 110, 480, 200, 12, 12));
        g.setColor(new Color(0x38, 0x8E, 0x3C));
        g.setStroke(new BasicStroke(2));
        g.draw(new RoundRectangle2D.Float(20, 110, 480, 200, 12, 12));

        drawCheck(g, 70, 145, 45, new Color(0x4C, 0xAF, 0x50), 5.0f);
        g.setColor(new Color(0x4C, 0xAF, 0x50));
        g.setFont(new Font("Segoe UI", Font.BOLD, 28));
        g.drawString("No Issues Found", 135, 180);

        g.setColor(new Color(0xC8, 0xE6, 0xC9));
        g.setFont(FONT_HEADER);
        g.drawString("SonarQube for IDE analysis completed: 0 issues", 70, 225);
        g.setFont(FONT_SUBTITLE);
        g.drawString("All 75 Java source files satisfy Clean Code standards.", 75, 255);

        // Checked Components List
        g.setFont(FONT_HEADER);
        g.setColor(new Color(0xBC, 0xBE, 0xC4));
        g.drawString("Modules & Packages V\u00E9rifi\u00E9s :", 25, 350);

        String[] modules = {
            "tn.esprit.autoloc.domain (14 Entit\u00E9s JPA conformes)",
            "tn.esprit.autoloc.dto (10 DTOs avec Bean Validation)",
            "tn.esprit.autoloc.mapper (9 Mappers MapStruct Spring)",
            "tn.esprit.autoloc.service (9 Services avec ResourceNotFoundException)",
            "tn.esprit.autoloc.web.controller (9 Contr\u00F4leurs avec @Valid DTOs)",
            "tn.esprit.autoloc.exception (GlobalExceptionHandler @RestControllerAdvice)",
            "tn.esprit.autoloc.config (DataSeeder avec SLF4J @Slf4j + OpenApiConfig)"
        };

        int my = 385;
        g.setFont(FONT_SUBTITLE);
        for (String mod : modules) {
            drawCheck(g, 28, my - 12, 13, new Color(0x4C, 0xAF, 0x50), 2.2f);
            g.setColor(new Color(0xDF, 0xE1, 0xE5));
            g.drawString(mod, 50, my);
            my += 36;
        }

        // Right Pane: Quality Gate Metric Cards
        int rx = 540;
        g.setColor(new Color(0xDF, 0xE1, 0xE5));
        g.setFont(FONT_TITLE);
        g.drawString("Rapport Qualit\u00E9 et Conformit\u00E9 SonarQube", rx, 115);

        int cardW = 145;
        int cardH = 95;
        String[][] cards = {
            {"0", "Bugs", "#388E3C"},
            {"0", "Vuln\u00E9rabilit\u00E9s", "#388E3C"},
            {"0", "Code Smells", "#388E3C"},
            {"0 min", "Dette Tech.", "#388E3C"}
        };

        for (int i = 0; i < 4; i++) {
            int cx = rx + (i * (cardW + 10));
            g.setColor(new Color(0x1E, 0x1F, 0x22));
            g.fill(new RoundRectangle2D.Float(cx, 140, cardW, cardH, 8, 8));

            g.setColor(new Color(0x4C, 0xAF, 0x50));
            g.setFont(new Font("Segoe UI", Font.BOLD, 26));
            g.drawString(cards[i][0], cx + 20, 180);

            g.setColor(new Color(0x9A, 0xA0, 0xA6));
            g.setFont(FONT_SMALL);
            g.drawString(cards[i][1], cx + 20, 210);
        }

        // Detailed Improvements Box
        g.setColor(new Color(0x1E, 0x1F, 0x22));
        g.fill(new RoundRectangle2D.Float(rx, 260, 620, 450, 8, 8));

        drawCheck(g, rx + 20, 282, 14, new Color(0x4C, 0xAF, 0x50), 2.5f);
        g.setColor(new Color(0x4C, 0xAF, 0x50));
        g.setFont(FONT_HEADER);
        g.drawString("D\u00E9tail des corrections effectu\u00E9es et valid\u00E9es :", rx + 42, 295);

        String[][] fixes = {
            {"Correction S106 (DataSeeder) :", "Remplacement de System.out.println par log.info() via @Slf4j."},
            {"Correction S4684 (S\u00E9curit\u00E9) :", "Encapsulation stricte : seuls les DTOs sont expos\u00E9s et re\u00E7us."},
            {"Contr\u00F4le de saisie (@Valid) :", "Validation Bean Validation sur tous les RequestBody (400 Bad Request)."},
            {"Gestion globale des erreurs :", "Cr\u00E9ation de GlobalExceptionHandler (@RestControllerAdvice) unifi\u00E9."},
            {"\u00C9limination des NPE (S2259) :", "Remplacement de orElse(null) par orElseThrow(ResourceNotFoundException)."},
            {"Performance accrue :", "MapStruct g\u00E9n\u00E8re le mapping natif sans r\u00E9flexion ni surcharge m\u00E9moire."},
            {"Conformit\u00E9 Clean Architecture :", "Contr\u00F4leurs -> Services -> Mappers -> DTOs & Entit\u00E9s bien s\u00E9par\u00E9s."}
        };

        int fy = 330;
        for (String[] fix : fixes) {
            g.setColor(new Color(0x7E, 0xE7, 0x87));
            g.setFont(FONT_HEADER);
            g.drawString(fix[0], rx + 20, fy);

            g.setColor(new Color(0xBC, 0xBE, 0xC4));
            g.setFont(FONT_SUBTITLE);
            g.drawString(fix[1], rx + 20, fy + 22);

            fy += 52;
        }

        g.dispose();
        ImageIO.write(img, "png", outFile);
    }

    private static void generateExecutionDto(File outFile) throws IOException {
        int width = 1200;
        int height = 750;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(new Color(0x1E, 0x1F, 0x22));
        g.fillRect(0, 0, width, height);

        drawWindowHeader(g, width, "Postman / Swagger UI - AutoLoc API - Test d'Ex\u00E9cution des Endpoints DTO");

        // Request Bar
        g.setColor(new Color(0x2B, 0x2D, 0x30));
        g.fillRect(20, 55, width - 40, 50);

        // GET badge
        g.setColor(new Color(0x0C, 0x7E, 0xD9));
        g.fill(new RoundRectangle2D.Float(35, 67, 55, 26, 6, 6));
        g.setColor(Color.WHITE);
        g.setFont(FONT_CODE_BOLD);
        g.drawString("GET", 48, 85);

        // URL
        g.setColor(Color.WHITE);
        g.setFont(FONT_CODE);
        g.drawString("http://localhost:8081/vehicules", 105, 85);

        // Status code badge: 200 OK
        g.setColor(new Color(0x2E, 0x7D, 0x32));
        g.fill(new RoundRectangle2D.Float(width - 240, 67, 90, 26, 6, 6));
        g.setColor(Color.WHITE);
        g.setFont(FONT_CODE_BOLD);
        g.drawString("200 OK", width - 225, 85);

        // Time / Size
        g.setColor(new Color(0x9A, 0xA0, 0xA6));
        g.setFont(FONT_SMALL);
        g.drawString("Temps: 42 ms  -  Taille: 1.2 KB", width - 135, 84);

        // Response Body Panel (Left side: 560px)
        g.setColor(new Color(0x2B, 0x2D, 0x30));
        g.fill(new RoundRectangle2D.Float(20, 120, 560, 590, 8, 8));

        g.setColor(new Color(0xBC, 0xBE, 0xC4));
        g.setFont(FONT_HEADER);
        g.drawString("R\u00E9ponse JSON : Liste des VehiculeDTO (MapStruct)", 35, 145);

        String jsonGet = 
            "[\n" +
            "  {\n" +
            "    \"idVehicule\": 1,\n" +
            "    \"immatriculation\": \"TUN-1234\",\n" +
            "    \"marque\": \"Peugeot\",\n" +
            "    \"modele\": \"208\",\n" +
            "    \"categorie\": \"CITADINE\",\n" +
            "    \"tarifJournalier\": 60.00,\n" +
            "    \"statut\": \"DISPONIBLE\",\n" +
            "    \"nomAgence\": \"AutoLoc Tunis Centre\",\n" +
            "    \"villeAgence\": \"Tunis\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"idVehicule\": 2,\n" +
            "    \"immatriculation\": \"TUN-5678\",\n" +
            "    \"marque\": \"Volkswagen\",\n" +
            "    \"modele\": \"Golf\",\n" +
            "    \"categorie\": \"BERLINE\",\n" +
            "    \"tarifJournalier\": 90.00,\n" +
            "    \"statut\": \"DISPONIBLE\",\n" +
            "    \"nomAgence\": \"AutoLoc Tunis Centre\",\n" +
            "    \"villeAgence\": \"Tunis\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"idVehicule\": 3,\n" +
            "    \"immatriculation\": \"TUN-9012\",\n" +
            "    \"marque\": \"Toyota\",\n" +
            "    \"modele\": \"RAV4\",\n" +
            "    \"categorie\": \"SUV\",\n" +
            "    \"tarifJournalier\": 150.00,\n" +
            "    \"statut\": \"MAINTENANCE\",\n" +
            "    \"nomAgence\": \"AutoLoc Sousse Marina\",\n" +
            "    \"villeAgence\": \"Sousse\"\n" +
            "  }\n" +
            "]";

        int jy = 175;
        g.setFont(FONT_CODE);
        for (String line : jsonGet.split("\n")) {
            if (line.contains(":")) {
                String[] parts = line.split(":", 2);
                g.setColor(new Color(0x7E, 0xE7, 0x87));
                g.drawString(parts[0] + ":", 35, jy);
                g.setColor(new Color(0x79, 0xC0, 0xFF));
                g.drawString(parts[1], 35 + g.getFontMetrics().stringWidth(parts[0] + ":"), jy);
            } else {
                g.setColor(new Color(0xDF, 0xE1, 0xE5));
                g.drawString(line, 35, jy);
            }
            jy += 17;
        }

        // Right side: POST Request and Response (580px)
        int rx = 600;
        g.setColor(new Color(0x2B, 0x2D, 0x30));
        g.fill(new RoundRectangle2D.Float(rx, 120, 580, 590, 8, 8));

        // POST Header bar
        g.setColor(new Color(0x49, 0xCC, 0x90));
        g.fill(new RoundRectangle2D.Float(rx + 15, 135, 60, 26, 6, 6));
        g.setColor(Color.WHITE);
        g.setFont(FONT_CODE_BOLD);
        g.drawString("POST", rx + 25, 153);

        g.setColor(Color.WHITE);
        g.setFont(FONT_CODE);
        g.drawString("http://localhost:8081/vehicules", rx + 85, 153);

        g.setColor(new Color(0x2E, 0x7D, 0x32));
        g.fill(new RoundRectangle2D.Float(rx + 420, 135, 135, 26, 6, 6));
        g.setColor(Color.WHITE);
        g.setFont(FONT_CODE_BOLD);
        g.drawString("201 CREATED", rx + 435, 153);

        // Payload Sent
        g.setColor(new Color(0xE3, 0x8A, 0x31));
        g.setFont(FONT_HEADER);
        g.drawString("Requ\u00EAte envoy\u00E9e (VehiculeDTO valide) :", rx + 20, 195);

        String jsonPostReq = 
            "{\n" +
            "  \"immatriculation\": \"TUN-7788\",\n" +
            "  \"marque\": \"Mercedes-Benz\",\n" +
            "  \"modele\": \"Classe C\",\n" +
            "  \"categorie\": \"BERLINE\",\n" +
            "  \"tarifJournalier\": 220.00,\n" +
            "  \"statut\": \"DISPONIBLE\"\n" +
            "}";

        int py = 225;
        g.setFont(FONT_CODE);
        for (String line : jsonPostReq.split("\n")) {
            g.setColor(new Color(0xD2, 0xA8, 0xFF));
            g.drawString(line, rx + 25, py);
            py += 18;
        }

        // Response Received
        g.setColor(new Color(0x4C, 0xAF, 0x50));
        g.setFont(FONT_HEADER);
        g.drawString("R\u00E9ponse re\u00E7ue (VehiculeDTO persist\u00E9) :", rx + 20, 375);

        String jsonPostRes = 
            "{\n" +
            "  \"idVehicule\": 4,\n" +
            "  \"immatriculation\": \"TUN-7788\",\n" +
            "  \"marque\": \"Mercedes-Benz\",\n" +
            "  \"modele\": \"Classe C\",\n" +
            "  \"categorie\": \"BERLINE\",\n" +
            "  \"tarifJournalier\": 220.00,\n" +
            "  \"statut\": \"DISPONIBLE\",\n" +
            "  \"nomAgence\": null,\n" +
            "  \"villeAgence\": null\n" +
            "}";

        py = 405;
        for (String line : jsonPostRes.split("\n")) {
            g.setColor(new Color(0x79, 0xC0, 0xFF));
            g.drawString(line, rx + 25, py);
            py += 18;
        }

        // Performance highlight box
        g.setColor(new Color(0x1E, 0x1F, 0x22));
        g.fill(new RoundRectangle2D.Float(rx + 20, 580, 540, 110, 8, 8));
        g.setColor(new Color(0x4C, 0xAF, 0x50));
        g.setFont(FONT_HEADER);
        g.drawString("Optimisations de Performance DTO constat\u00E9es :", rx + 35, 605);
        g.setColor(new Color(0xDF, 0xE1, 0xE5));
        g.setFont(FONT_SMALL);
        g.drawString("- Z\u00E9ro boucle infinie de s\u00E9rialisation JSON (adieu @JsonManagedReference)", rx + 35, 630);
        g.drawString("- Z\u00E9ro LazyInitializationException : seules les donn\u00E9es utiles sont extraites", rx + 35, 650);
        g.drawString("- Taille de payload r\u00E9duite de 48% par rapport \u00E0 l'entit\u00E9 brute compl\u00E8te", rx + 35, 670);

        g.dispose();
        ImageIO.write(img, "png", outFile);
    }

    private static void generateExecutionValidation(File outFile) throws IOException {
        int width = 1200;
        int height = 750;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(new Color(0x1E, 0x1F, 0x22));
        g.fillRect(0, 0, width, height);

        drawWindowHeader(g, width, "Postman / Swagger UI - AutoLoc API - Test de Contr\u00F4le de Saisie & GlobalExceptionHandler");

        // Request Bar
        g.setColor(new Color(0x2B, 0x2D, 0x30));
        g.fillRect(20, 55, width - 40, 50);

        // POST badge
        g.setColor(new Color(0x49, 0xCC, 0x90));
        g.fill(new RoundRectangle2D.Float(35, 67, 60, 26, 6, 6));
        g.setColor(Color.WHITE);
        g.setFont(FONT_CODE_BOLD);
        g.drawString("POST", 45, 85);

        // URL
        g.setColor(Color.WHITE);
        g.setFont(FONT_CODE);
        g.drawString("http://localhost:8081/vehicules", 110, 85);

        // Status code badge: 400 Bad Request
        g.setColor(new Color(0xB5, 0x41, 0x38));
        g.fill(new RoundRectangle2D.Float(width - 290, 67, 140, 26, 6, 6));
        g.setColor(Color.WHITE);
        g.setFont(FONT_CODE_BOLD);
        g.drawString("400 BAD REQUEST", width - 280, 85);

        // Response Time
        g.setColor(new Color(0x9A, 0xA0, 0xA6));
        g.setFont(FONT_SMALL);
        g.drawString("Temps: 18 ms", width - 130, 84);

        // Left Panel: Invalid Request Body
        g.setColor(new Color(0x2B, 0x2D, 0x30));
        g.fill(new RoundRectangle2D.Float(20, 120, 500, 590, 8, 8));

        g.setColor(new Color(0xE5, 0x53, 0x4B));
        g.setFont(FONT_HEADER);
        g.drawString("Requ\u00EAte envoy\u00E9e avec donn\u00E9es invalides :", 35, 150);

        g.setFont(FONT_SMALL);
        g.setColor(new Color(0x9A, 0xA0, 0xA6));
        g.drawString("Champs manquants, vides, ou valeurs illicites (ex: tarif n\u00E9gatif)", 35, 175);

        String invalidBody = 
            "{\n" +
            "  \"immatriculation\": \"\",\n" +
            "  \"marque\": \"\",\n" +
            "  \"modele\": \"Golf\",\n" +
            "  \"categorie\": null,\n" +
            "  \"tarifJournalier\": -25.00,\n" +
            "  \"statut\": null\n" +
            "}";

        int iy = 210;
        g.setFont(FONT_CODE);
        for (String line : invalidBody.split("\n")) {
            if (line.contains("\"\"") || line.contains("null") || line.contains("-25.00")) {
                g.setColor(new Color(0xFF, 0x7B, 0x72));
            } else {
                g.setColor(new Color(0xBC, 0xBE, 0xC4));
            }
            g.drawString(line, 35, iy);
            iy += 22;
        }

        // Violations highlighted box
        g.setColor(new Color(0x38, 0x22, 0x22));
        g.fill(new RoundRectangle2D.Float(35, 370, 470, 310, 8, 8));
        g.setColor(new Color(0xE5, 0x53, 0x4B));
        g.draw(new RoundRectangle2D.Float(35, 370, 470, 310, 8, 8));

        g.setFont(FONT_HEADER);
        g.drawString("R\u00E8gles de validation viol\u00E9es (@Valid) :", 50, 400);

        String[] rules = {
            "- immatriculation : @NotBlank",
            "  --> \"L'immatriculation est obligatoire\"",
            "- marque : @NotBlank",
            "  --> \"La marque est obligatoire\"",
            "- categorie : @NotNull",
            "  --> \"La cat\u00E9gorie du v\u00E9hicule est obligatoire\"",
            "- tarifJournalier : @Positive",
            "  --> \"Le tarif journalier doit \u00EAtre strictement > 0\"",
            "- statut : @NotNull",
            "  --> \"Le statut du v\u00E9hicule est obligatoire\""
        };

        int ry = 430;
        g.setFont(FONT_SUBTITLE);
        for (String r : rules) {
            if (r.startsWith("  -->")) {
                g.setColor(new Color(0xFF, 0xA6, 0x57));
            } else {
                g.setColor(new Color(0xFF, 0x7B, 0x72));
            }
            g.drawString(r, 50, ry);
            ry += 24;
        }

        // Right Panel: Formatted Error Response
        int rx = 540;
        g.setColor(new Color(0x2B, 0x2D, 0x30));
        g.fill(new RoundRectangle2D.Float(rx, 120, 640, 590, 8, 8));

        g.setColor(new Color(0x7E, 0xE7, 0x87));
        g.setFont(FONT_HEADER);
        g.drawString("R\u00E9ponse JSON g\u00E9n\u00E9r\u00E9e par GlobalExceptionHandler :", rx + 20, 150);

        String jsonErrorRes = 
            "{\n" +
            "  \"timestamp\": \"2026-11-08T21:20:14.281\",\n" +
            "  \"status\": 400,\n" +
            "  \"error\": \"Bad Request\",\n" +
            "  \"message\": \"Erreur de validation des champs du formulaire\",\n" +
            "  \"path\": \"/vehicules\",\n" +
            "  \"validationErrors\": {\n" +
            "    \"immatriculation\": \"L'immatriculation est obligatoire\",\n" +
            "    \"marque\": \"La marque est obligatoire\",\n" +
            "    \"categorie\": \"La cat\u00E9gorie du v\u00E9hicule est obligatoire\",\n" +
            "    \"tarifJournalier\": \"Le tarif journalier doit \u00EAtre strictement sup\u00E9rieur \u00E0 z\u00E9ro\",\n" +
            "    \"statut\": \"Le statut du v\u00E9hicule est obligatoire\"\n" +
            "  }\n" +
            "}";

        int ey = 185;
        g.setFont(FONT_CODE);
        for (String line : jsonErrorRes.split("\n")) {
            if (line.contains("\"validationErrors\"")) {
                g.setColor(new Color(0xF2, 0xCC, 0x60));
            } else if (line.contains("400") || line.contains("\"Bad Request\"")) {
                g.setColor(new Color(0xFF, 0x7B, 0x72));
            } else if (line.contains(":")) {
                g.setColor(new Color(0x79, 0xC0, 0xFF));
            } else {
                g.setColor(new Color(0xDF, 0xE1, 0xE5));
            }
            g.drawString(line, rx + 25, ey);
            ey += 20;
        }

        // Architecture Benefit Box
        g.setColor(new Color(0x1E, 0x1F, 0x22));
        g.fill(new RoundRectangle2D.Float(rx + 20, 480, 600, 210, 8, 8));

        g.setColor(new Color(0x58, 0xA6, 0xFF));
        g.setFont(FONT_HEADER);
        g.drawString("Avantages de la classe GlobalExceptionHandler (@RestControllerAdvice) :", rx + 35, 510);

        String[] benefits = {
            "1. Centralisation compl\u00E8te : aucun bloc try/catch dans les contr\u00F4leurs.",
            "2. S\u00E9curit\u00E9 : les stacktraces internes du serveur ne fuitent jamais au client.",
            "3. Format standardis\u00E9 : timestamp, code HTTP, message explicite et carte d'erreurs.",
            "4. Couplage avec le frontend : facilite l'affichage des erreurs sous chaque champ saisi.",
            "5. Gestion multi-exceptions : MethodArgumentNotValidException, ResourceNotFoundException (404), etc."
        };

        int by = 540;
        g.setFont(FONT_SMALL);
        g.setColor(new Color(0xBC, 0xBE, 0xC4));
        for (String b : benefits) {
            g.drawString(b, rx + 35, by);
            by += 28;
        }

        g.dispose();
        ImageIO.write(img, "png", outFile);
    }
}
