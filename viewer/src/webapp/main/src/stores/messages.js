
// initial state
const state = {
    messages: [],
    nextId: 0
};

// getters
const getters = {};

// actions
const actions = {
    add({commit}, payload) {
        const x = state.nextId + 1;
        commit('addMessage', { id: x, text: payload.text, type: payload.type });
        setTimeout(() => {
            this.dispatch('messages/remove', { id: x});
        }, 10000)
    },
    remove({commit}, payload) {
        commit('removeMessage', {id: payload.id})
    },
    clear({commit}) {
        commit('clear')
    }
};

// mutations
const mutations = {
    addMessage(state, payload) {
        state.id = payload.id;
        state.messages.push({
            id: payload.id,
            text: payload.text,
            type: payload.type
        })
    },
    removeMessage(state, payload) {
        state.messages = state.messages.filter(msg => !(msg.id === payload.id))
    },
    clear() {
        state.messages = []
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}

