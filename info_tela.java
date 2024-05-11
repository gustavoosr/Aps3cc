package cronometroaps;

public class info_tela {
    private String codigo_eqi;
    private String nome_vei;
    private String nome_equi;
    private String nome_pil;
    private String volta1;
    private String volta2;
    private String voltatot;
    private String block;
    public info_tela(String codigo_eqi, String nome_vei,String nome_equi, String nome_pil, String volta1,String volta2, String voltatot, String block) {
        this.codigo_eqi = codigo_eqi;
        this.nome_vei = nome_vei;
        this.nome_equi = nome_equi;
        this.nome_pil = nome_pil;
        this.volta1 = volta1;
        this.volta2 = volta2;
        this.voltatot = voltatot;
        this.block = block;
    }
    public String getCodigo_eqi() {
        return codigo_eqi;
    }
    public String getNome_vei(){
        return nome_vei;
    }
    public String getNome_equi() {
        return nome_equi;
    }
    public String getNome_pil() {
        return nome_pil;
    }
    public String getVolta1() {
        return volta1;
    }
    public String getVolta2() {
        return volta2;
    }
    public String getVoltatot() {
        return voltatot;
    }
    public String getBlock(){
        return block;
    }
}
