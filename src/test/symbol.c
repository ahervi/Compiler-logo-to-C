#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include <stdbool.h>
#define SDL_MAIN_HANDLED
#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include "logo.h"
const int WIN_WIDTH = 640;
const int WIN_HEIGHT = 480;

int main(int argc, char ** argv) {
  bool quit = false;
  SDL_Event event;
  SDL_Init(SDL_INIT_VIDEO);
  SDL_Window * window = SDL_CreateWindow("src\\test\\symbol.logo",
  SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED, WIN_WIDTH, WIN_HEIGHT, 0);
  SDL_Renderer * renderer = SDL_CreateRenderer(window, -1, 0);
  SDL_SetRenderDrawColor(renderer, 255, 255, 255, SDL_ALPHA_OPAQUE);
  SDL_RenderClear(renderer);
SDL_SetRenderDrawColor(renderer, 0, 0, 0, SDL_ALPHA_OPAQUE);
  float xInit = WIN_WIDTH/2;
  float yInit = WIN_HEIGHT/2;
  float x;
  float y;
  double pi = M_PI;
  double rotation = pi/2;
  float foo = 1.0;

  showString("foo");
clearScreen();
  showFloat(foo);
clearScreen();
  SDL_RenderPresent(renderer);
  while (!quit){
    SDL_Delay(10);
    SDL_PollEvent(&event);
    switch (event.type){
       case SDL_QUIT:
       quit = true;
       break;
    }
  }
  SDL_DestroyRenderer(renderer);
  SDL_DestroyWindow(window);
  SDL_Quit();
  return 0;
}
