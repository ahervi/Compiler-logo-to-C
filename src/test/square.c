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
  SDL_Window * window = SDL_CreateWindow("src\\test\\square.logo",
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
  void square (float length) {
for (int i = 0; i < 4.0; i++) {
	x = xInit - length*sin(rotation*pi/180);
y = yInit - length*cos(rotation*pi/180);
SDL_RenderDrawLine(renderer, xInit, yInit, x, y);
xInit = x;
yInit = y;

	rotation = rotation - 90.0;

}}

  void randomcolor (void) {
int tab[6][3] = {{255, 0, 0}, {255, 153, 0}, {255, 255, 0}, {0, 255, 0}, {0, 0, 255}, {127, 0, 255}};
int color[3] = {tab[rand() % (6+1)][0], tab[rand() % (6+1)][1], tab[rand() % (6+1)][2]} ;
setpencolor(color, renderer);}

  
  for (int i = 0; i < 36.0; i++) {
	randomcolor();

	square(random((int)200.0));

	rotation = rotation - 10.0;

}
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
