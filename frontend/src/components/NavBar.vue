<template>
  <nav class="navbar is-primary is-radiusless" role="navigation">
    <div class="container">
      <div class="navbar-brand">
        <a href="/" class="navbar-item">
          <img class="mr-3" src="/img/icon_white.png" alt="A keyboard icon as logo for Play-Together-Keyboard" />
          <span class="is-uppercase">Play together Keyboard</span>
        </a>
      </div>
      <div id="menuItems" class="navbar-menu">
        <div class="navbar-start"></div>
        <div class="navbar-end">
          <div class="navbar-item">
            <a class="button is-text" title="Goto GitHub repository" href="https://github.com/Poeschl/PlayTogetherKeyboard">
              <div class="icon">
                <FontAwesomeIcon icon="fa-brands fa-github" class="fa-xl" />
              </div>
            </a>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss"></style>
