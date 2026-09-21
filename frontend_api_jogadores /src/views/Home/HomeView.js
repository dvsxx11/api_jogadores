import { ref, onMounted } from "vue";
import api from "../../services/api";

export default {
  name: "HomeView",
  setup() {
    const jogadores = ref([]);
    const mensagemErro = ref("");

    const carregarJogadores = async () => {
      try {
        const resposta = await api.get("/jogadores");
        jogadores.value = resposta.data;
      } catch (erro) {
        mensagemErro.value = "Erro ao carregar os dados. A API está rodando?";
        console.error(erro);
      }
    };

    const apagarJogador = async (id) => {
      if (confirm("Tem certeza que deseja apagar este jogador?")) {
        try {
          await api.delete(`/jogadores/${id}`);
          carregarJogadores();
        } catch (erro) {
          alert("Erro ao apagar jogador");
        }
      }
    };

    onMounted(() => {
      carregarJogadores();
    });

    return {
      jogadores,
      mensagemErro,
      apagarJogador,
    };
  },
};
