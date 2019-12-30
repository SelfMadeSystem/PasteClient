// 
// Decompiled by Procyon v0.5.30
// 

package uwu.smsgamer.PasteClient.AltManager;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import uwu.smsgamer.PasteClient.ClientBase;

import java.net.Proxy;

public class AltLoginThread extends Thread {
    private final String password;
    private String status;
    private final String username;
    private Minecraft mc;

    public AltLoginThread(final String username, final String password) {
        super("Alt Login Thread");
        this.mc = Minecraft.getMinecraft();
        this.username = username;
        this.password = password;
        this.status = "Waiting...";
    }

    private Session createSession(final String username, final String password) {
        final YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        final YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service.createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername(username);
        auth.setPassword(password);
        try {
            auth.logIn();
            return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
        } catch (AuthenticationException localAuthenticationException) {
            localAuthenticationException.printStackTrace();
            return null;
        }
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public void run() {
        if (this.password.equals("")) {
            this.mc.session = new Session(this.username, "", "", "mojang");
            this.status = "Logged in. (" + this.username + " - cracked)";
            return;
        }
        this.status = "Logging in...";
        final Session auth = this.createSession(this.username, this.password);
        if (auth == null) {
            this.status = "Login failed!";
        } else {
            final AltManager altManager = ClientBase.INSTANCE.altManager;
            AltManager.lastAlt = new Alt(this.username, this.password);
            this.status = "Logged in. (" + auth.getUsername() + ")";
            this.mc.session = auth;
        }
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
