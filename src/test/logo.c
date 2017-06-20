#include <stdio.h>
#include <stdbool.h>
#include<stdlib.h>
#include<time.h>
#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
void showFloat(float i){
    printf("%1.1f\n", i);
}
void showBool(bool i) {
    printf("%i\n", i);
}
void showString(char s[]) {
    printf("%s\n", s);
}

void clearScreen(){}

float random(int a) {
return rand() % (a + 1);}


void setpencolor(int pick[3], SDL_Renderer * renderer) {

    SDL_SetRenderDrawColor(renderer, pick[0], pick[1], pick[2], SDL_ALPHA_OPAQUE);
    return;}