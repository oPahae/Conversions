package projs;
import javax.swing.*;

public class InlineHTMLInterface {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Interface HTML");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            String htmlContent = """
                <!DOCTYPE html>
                <html lang="fr">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Page de Connexion</title>
                    <style>
                        body {
                            font-family: 'Arial', sans-serif;
                            background: linear-gradient(to right, #6a11cb, #2575fc);
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                            margin: 0;
                            color: #fff;
                        }
                        .login-container {
                            background-color: rgba(255, 255, 255, 0.9);
                            border-radius: 15px;
                            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
                            padding: 30px;
                            width: 350px;
                            text-align: center;
                        }
                        h2 {
                            margin-bottom: 20px;
                            color: #333;
                            font-weight: bold;
                        }
                        .input-group {
                            margin-bottom: 20px;
                            text-align: left;
                            border-radius: 10px;
                        }
                        label {
                            margin-bottom: 5px;
                            color: #555;
                            font-weight: bold;
                        }
                        input[type="text"],
                        input[type="password"] {
                            width: 100%;
                            padding: 10px;
                            border: 2px solid #ccc;
                            border-radius: 5px;
                            box-sizing: border-box;
                            transition: border 0.3s, box-shadow 0.3s;
                            font-size: 14px;
                        }
                        input[type="text"]:focus,
                        input[type="password"]:focus {
                            border-color: #2575fc;
                            box-shadow: 0 0 5px rgba(37, 117, 252, 0.5);
                            outline: none;
                        }
                        .login-button {
                            background-color: #2575fc;
                            color: white;
                            border: none;
                            padding: 10px;
                            border-radius: 5px;
                            cursor: pointer;
                            width: 100%;
                            font-size: 16px;
                            transition: background 0.3s, transform 0.3s;
                            font-weight: bold;
                        }
                        .login-button:hover {
                            background-color: #1e60d2;
                            transform: translateY(-2px);
                        }
                        .forgot-password {
                            margin-top: 10px;
                        }
                        .forgot-password a {
                            color: #2575fc;
                            text-decoration: none;
                            font-weight: bold;
                        }
                        .forgot-password a:hover {
                            text-decoration: underline;
                        }
                    </style>
                </head>
                <body>
                    <div class="login-container">
                        <h2>Connexion</h2>
                        <div class="input-group">
                            <label for="username">Nom d'utilisateur</label>
                            <input type="text" id="username" placeholder="Entrez votre nom d'utilisateur" required>
                        </div>
                        <div class="input-group">
                            <label for="password">Mot de passe</label>
                            <input type="password" id="password" placeholder="Entrez votre mot de passe" required>
                        </div>
                        <button class="login-button">Se connecter</button>
                        <div class="forgot-password">
                            <a href="#">Mot de passe oublié ?</a>
                        </div>
                    </div>
                </body>
                </html>
                """;

            JEditorPane editorPane = new JEditorPane("text/html", htmlContent);
            editorPane.setEditable(false);
            editorPane.setSize(800, 600);
            frame.add(new JScrollPane(editorPane));
            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}