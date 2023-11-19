package uy.edu.um.airport.entities.Terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.persistence.TerminalRepository;

@Service
public class TerminalMgr {

    @Autowired
    private TerminalRepository terminalRepository;

    public void addTerminal(Terminal terminal) {
        terminalRepository.save(terminal);
    }

    public Terminal findTerminalByNombre(String id) {
        return terminalRepository.findById(id).orElse(null);
    }

    // Más métodos según tus necesidades
}
