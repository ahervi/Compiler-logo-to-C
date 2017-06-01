struct list {
    int size;
    char** content;
};

typedef struct list * plist;

void clearScreen();
void showNUM(float i);
void showBOOL(bool i);
void showSTR(char s[]);
void showLIST(plist p);

float random(int a);
char* pick(plist p);
void setpencolor(char pick[], SDL_Renderer * renderer);
