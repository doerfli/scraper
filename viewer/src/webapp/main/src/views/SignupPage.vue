<template>
    <div>
        <HeroHeader title="Sign up for an account" subtitle="to view feeds from many sources in one place"/>
        <section>
            <div class="container">
                <div class="columns">
                    <div class="column is-half">
                        <Signup />
                    </div>
                    <div class="column">
                    </div>
                </div>
            </div>
        </section>
        <BetaInfo />
        <FAQ />
    </div>
</template>

<script>
    import Signup from "../components/login/Signup";
    import HeroHeader from "../components/HeroHeader";
    import BetaInfo from "../components/login/BetaInfo";
    import FAQ from "../components/login/FAQ";

    export default {
        name: "SignupPage",
        components: {
            BetaInfo,
            HeroHeader,
            Signup,
            FAQ
        }
    }
</script>

<style scoped lang="scss">
</style>
