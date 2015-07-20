/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.colabdario;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author matheus
 */
@ServerEndpoint("/colaborativeclassendpoint")
public class ColaborativeClass {

    public ColaborativeClass() {
        this.text = "";
    }
    
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private String text;
    @OnMessage
    public String onMessage(String message) throws IOException {
        System.out.println("Message Received!");
        this.text = message;
        System.out.println(this.text);
        for(Session peer : peers){
            peer.getBasicRemote().sendText(text);
        }
        return "Message has been broadcasted.";
    }
    @OnOpen
    public void onOpen (Session peer) {
        peers.add(peer);
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }
}
